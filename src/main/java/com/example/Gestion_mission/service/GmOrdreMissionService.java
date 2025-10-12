package com.example.Gestion_mission.service;

import com.example.Gestion_mission.dto.ClotureRhRequest;
import com.example.Gestion_mission.dto.FinalisationMgRequest;
import com.example.Gestion_mission.dto.MissionCreationRequest;
import com.example.Gestion_mission.dto.MissionDetailDTO;
import com.example.Gestion_mission.dto.OrdreMissionDTO;
import com.example.Gestion_mission.dto.RhValidationRequest;
import com.example.Gestion_mission.dto.ValidationCaisseRequest;
import com.example.Gestion_mission.dto.ValidationMgRequest;
import com.example.Gestion_mission.enums.MissionStatus;
import com.example.Gestion_mission.enums.WorkflowDecision;
import com.example.Gestion_mission.mapper.MissionDetailMapper;
import com.example.Gestion_mission.mapper.OrdreMissionMapper;
import com.example.Gestion_mission.model.GmAgent;
import com.example.Gestion_mission.model.GmBudgetMission;
import com.example.Gestion_mission.model.GmMissionEtape;
import com.example.Gestion_mission.model.GmMissionNotification;
import com.example.Gestion_mission.model.GmMissionRessource;
import com.example.Gestion_mission.model.GmOrdreMission;
import com.example.Gestion_mission.model.GmRemiseJustificatifs;
import com.example.Gestion_mission.model.GmValidationEtape;
import com.example.Gestion_mission.model.GmValidationWorkflow;
import com.example.Gestion_mission.repository.GmAgentRepository;
import com.example.Gestion_mission.repository.GmBudgetMissionRepository;
import com.example.Gestion_mission.repository.GmMissionEtapeRepository;
import com.example.Gestion_mission.repository.GmMissionNotificationRepository;
import com.example.Gestion_mission.repository.GmMissionRessourceRepository;
import com.example.Gestion_mission.repository.GmOrdreMissionRepository;
import com.example.Gestion_mission.repository.GmRemiseJustificatifsRepository;
import com.example.Gestion_mission.repository.GmRessourceRepository;
import com.example.Gestion_mission.repository.GmValidationEtapeRepository;
import com.example.Gestion_mission.repository.GmValidationWorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GmOrdreMissionService {

    private static final Logger logger = LoggerFactory.getLogger(GmOrdreMissionService.class);

    @Autowired
    private GmOrdreMissionRepository ordreMissionRepository;

    @Autowired
    private JournalUtilisateurService journalService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private OrdreMissionMapper mapper;

    @Autowired
    private MissionDetailMapper missionDetailMapper;

    @Autowired
    private GmMissionRessourceRepository missionRessourceRepository;

    @Autowired
    private GmMissionEtapeRepository missionEtapeRepository;

    @Autowired
    private GmValidationWorkflowRepository validationWorkflowRepository;

    @Autowired
    private GmRemiseJustificatifsRepository remiseJustificatifsRepository;

    @Autowired
    private MissionAgentService missionAgentService;

    @Autowired
    private com.example.Gestion_mission.repository.GmRessourceRepository ressourceRepository;

    @Autowired
    private GmValidationEtapeRepository validationEtapeRepository;

    @Autowired
    private GmAgentRepository agentRepository;

    @Autowired
    private GmBudgetMissionRepository budgetMissionRepository;

    @Autowired
    private GmMissionNotificationRepository missionNotificationRepository;

    @Autowired
    private MissionPdfService missionPdfService;

    private static final String ETAPE_MG = "MG_BUDGET";
    private static final String ETAPE_CAISSE = "CAISSE_DISPO";
    private static final String ETAPE_FINALISATION = "MG_FINALISATION";
    private static final String ETAPE_RH_IMPRESSION = "RH_IMPRESSION";
    private static final String ETAPE_RH_VALIDATION = "RH_ANALYSE";

    private static final String PHASE_CHEF = "CHEF_SERVICE";
    private static final String PHASE_RH = "RH";
    private static final String PHASE_MG = "MOYENS_GENERAUX";
    private static final String PHASE_CAISSE = "CAISSE";
    private static final String PHASE_RH_FINAL = "RH_IMPRESSION";

    private static final String WORKFLOW_STATUT_EN_ATTENTE = "EN_ATTENTE";
    private static final String WORKFLOW_STATUT_TERMINE = "TERMINE";
    private static final String WORKFLOW_STATUT_REJETE = "REJETE";

    public List<OrdreMissionDTO> getAllOrdresMission() {
        // Enregistrer l'action de consultation
        journalService.enregistrerActionConsultation(
            roleService.getIdUtilisateurConnecte(), 
            "GM_ORDRE_MISSION", 
            "127.0.0.1", // À remplacer par l'IP réelle
            "User-Agent" // À remplacer par le vrai user agent
        );

        Long utilisateurId = roleService.getIdUtilisateurConnecte();
        List<GmOrdreMission> missions = ordreMissionRepository.findAll();
        logger.info("Utilisateur {} récupère {} missions", utilisateurId, missions.size());
        return mapper.toDTOList(missions);
    }

    public List<OrdreMissionDTO> listerParStatut(List<String> statuts) {
        if (statuts == null || statuts.isEmpty()) {
            return getAllOrdresMission();
        }

        List<String> codesNormalises = statuts.stream()
                .filter(s -> s != null && !s.isBlank())
                .map(String::trim)
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        journalService.enregistrerActionConsultation(
                roleService.getIdUtilisateurConnecte(),
                "GM_ORDRE_MISSION",
                "127.0.0.1",
                "User-Agent"
        );

        List<GmOrdreMission> missions = ordreMissionRepository.findByStatutMissionIn(codesNormalises);
        logger.info("Filtrage des missions par statuts {} -> {} résultats", codesNormalises, missions.size());
        return mapper.toDTOList(missions);
    }

    public List<OrdreMissionDTO> getMissionsPourRoleCourant() {
        if (roleService.utilisateurEstAdmin()) {
            return getAllOrdresMission();
        }

        String role = roleService.getRoleUtilisateur();
        List<String> statutsAutorises = statutsPourRole(role);
        if (statutsAutorises.isEmpty()) {
            return getAllOrdresMission();
        }
        return listerParStatut(statutsAutorises);
    }

    public Optional<GmOrdreMission> getOrdreMissionById(Long id) {
        // Enregistrer l'action de consultation
        journalService.enregistrerActionConsultation(
            roleService.getIdUtilisateurConnecte(), 
            "GM_ORDRE_MISSION", 
            "127.0.0.1", 
            "User-Agent"
        );
        
        return ordreMissionRepository.findById(id);
    }

    public Optional<MissionDetailDTO> getMissionDetail(Long id) {
        journalService.enregistrerActionConsultation(
            roleService.getIdUtilisateurConnecte(),
            "GM_ORDRE_MISSION",
            "127.0.0.1",
            "User-Agent"
        );

        return ordreMissionRepository.findById(id)
                .map(mission -> {
                    var ressources = missionRessourceRepository.findByOrdreMissionIdOrdreMission(id);
                    var etapes = missionEtapeRepository.findByOrdreMissionIdOrdreMissionOrderByOrdrePassageAsc(id);
                    var workflow = validationWorkflowRepository.findByOrdreMissionIdOrdreMissionOrderByOrdreValidationAsc(id);
                    GmRemiseJustificatifs justificatifs = remiseJustificatifsRepository.findByOrdreMissionIdOrdreMission(id)
                            .orElse(null);
                    GmBudgetMission budget = budgetMissionRepository.findByOrdreMissionIdOrdreMission(id)
                            .orElse(null);
                    var notifications = missionNotificationRepository.findByOrdreMissionIdOrdreMission(id);
                    var participants = missionAgentRepository.findByOrdreMissionIdOrdreMission(id);
                    return missionDetailMapper.toDetailDTO(mission, ressources, etapes, workflow, justificatifs, budget, notifications, participants);
                });
    }

    public GmOrdreMission createOrdreMission(MissionCreationRequest request) {
        if (request.getDateDebutMission() == null || request.getDateFinMission() == null) {
            throw new IllegalArgumentException("Les dates de début et fin sont obligatoires");
        }
        if (request.getDateFinMission().before(request.getDateDebutMission())) {
            throw new IllegalArgumentException("La date de fin doit être postérieure à la date de début");
        }

        Date maintenant = new Date();

        GmOrdreMission mission = new GmOrdreMission();
        mission.setObjetMission(request.getObjetMission());
        mission.setDateDebutMission(request.getDateDebutMission());
        mission.setDateFinMission(request.getDateFinMission());
        // Gérer les champs NULL pour lieuDepart et lieuDestination
        mission.setLieuDepart(request.getLieuDepart() != null && !request.getLieuDepart().isBlank() 
            ? request.getLieuDepart() : "-");
        mission.setLieuDestination(request.getLieuDestination() != null && !request.getLieuDestination().isBlank() 
            ? request.getLieuDestination() : "-");
        mission.setMotifMission(request.getMotifMission());
        mission.setEntiteCode(request.getEntiteCode());
        mission.setStatutMission(MissionStatus.EN_ATTENTE_VALIDATION_RH);
        mission.setWorkflowPhase(PHASE_RH);
        mission.setWorkflowStatut(WORKFLOW_STATUT_EN_ATTENTE);
        mission.setMotifRefusGlobal(null);
        mission.setPdfChefUri(null);
        mission.setPdfRhUri(null);
        mission.setPdfFinalUri(null);
        mission.setDateCreation(maintenant);
        mission.setDateMiseAJour(maintenant);
        mission.setCreatedBy(roleService.getRoleUtilisateur());
        mission.setIdAgentCreateur(roleService.getIdUtilisateurConnecte());
        mission.setFraisEstime(request.getFraisEstime());
        mission.setSignatureFondeRecue(0);
        mission.setSignatureAgentComptableRecue(0);
        mission.setDateValidationRh(null);
        mission.setDateValidationMg(null);
        mission.setDateValidationCaisse(null);
        mission.setAgentValidateurRh(null);
        mission.setAgentValidateurMg(null);
        mission.setAgentValidateurCaisse(null);

        journalService.enregistrerActionCreation(
            roleService.getIdUtilisateurConnecte(),
            "GM_ORDRE_MISSION",
            "Objet: " + mission.getObjetMission(),
            "127.0.0.1",
            "User-Agent"
        );

        GmOrdreMission saved = ordreMissionRepository.save(mission);

        if (saved.getCodeMission() == null || saved.getCodeMission().isBlank()) {
            saved.setCodeMission(genererCodeMission(saved.getIdOrdreMission()));
            saved = ordreMissionRepository.save(saved);
        }

        if (request.getParticipants() != null) {
            Long missionId = saved.getIdOrdreMission();
            request.getParticipants().forEach(participant ->
                    missionAgentService.ajouterParticipant(
                            missionId,
                            participant.getAgentId(),
                            participant.getRoleMission())
            );
        }

        if (request.getRessources() != null && !request.getRessources().isEmpty()) {
            nettoyerRessourcesMission(saved.getIdOrdreMission());
            persistMissionRessources(saved, request.getRessources());
        }

        if (request.getEtapes() != null && !request.getEtapes().isEmpty()) {
            nettoyerEtapesMission(saved.getIdOrdreMission());
            persistMissionEtapes(saved, request);
        }

        // Génération PDF (non-bloquant en cas d'erreur)
        try {
            missionPdfService.deleteIfExists(saved.getPdfChefUri());
            String pdfChef = missionPdfService.generateDocument(saved, MissionPdfService.DocumentType.CHEF_DEMANDE);
            saved.setPdfChefUri(pdfChef);
            saved = ordreMissionRepository.save(saved);
            logger.info("PDF chef généré avec succès: {}", pdfChef);
        } catch (Exception e) {
            logger.error("Échec génération PDF chef (non-bloquant): {}", e.getMessage(), e);
            saved.setPdfChefUri(null);
        }

        // Notification (non-bloquante en cas d'erreur)
        try {
            notifierRole(saved, "RH", "Nouvelle mission à valider", "MISSION_SUBMITTED");
        } catch (Exception e) {
            logger.warn("Échec envoi notification RH (non-bloquant): {}", e.getMessage(), e);
        }

        return saved;
    }

    public MissionDetailDTO validerParMg(Long missionId, ValidationMgRequest request) {
        GmOrdreMission mission = ordreMissionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("Mission introuvable"));
        verifierStatutMission(mission, List.of(MissionStatus.EN_ATTENTE_BUDGET_MG),
                "La mission n'est pas en attente de budgétisation");

        GmValidationEtape etapeMg = validationEtapeRepository.findByCodeEtape(ETAPE_MG)
                .orElseThrow(() -> new IllegalStateException("Étape MG introuvable"));

        GmValidationWorkflow workflow = validationWorkflowRepository
                .findByOrdreMissionIdOrdreMissionAndEtapeCodeEtape(missionId, ETAPE_MG)
                .orElseGet(() -> creerWorkflowInitial(mission, etapeMg));

        String decision = determinerDecision(request.getDecision(), WorkflowDecision.VALIDEE.name());
        boolean approuve = WorkflowDecision.VALIDEE.matches(decision);
        Date maintenant = new Date();

        workflow.setDecision(decision);
        workflow.setStatutValidation(approuve ? "VALIDEE" : "REJETEE");
        workflow.setCommentaires(request.getCommentaires());
        workflow.setMotifRefus(request.getMotifRefus());
        workflow.setMontantValide(request.getMontantValide());
        workflow.setDateValidation(maintenant);
        workflow.setUpdatedAt(maintenant);
        workflow.setUpdatedBy(roleService.getRoleUtilisateur());

        Long idValideur = roleService.getIdUtilisateurConnecte();
        GmAgent agentValideur = null;
        if (idValideur != null) {
            agentValideur = agentRepository.findById(idValideur).orElse(null);
            workflow.setAgentValideur(agentValideur);
        }

        GmBudgetMission budget = budgetMissionRepository.findByOrdreMissionIdOrdreMission(missionId)
                .orElse(new GmBudgetMission());
        budget.setOrdreMission(mission);
        budget.setMontantHebergement(request.getMontantHebergement());
        budget.setMontantRestauration(request.getMontantRestauration());
        budget.setMontantTransport(request.getMontantTransport());
        budget.setMontantCarburant(request.getMontantCarburant());
        budget.setMontantDivers(request.getMontantDivers());
        budget.setMontantTotal(request.getMontantValide());
        budget.setCommentaire(request.getCommentaires());
        budget.setUpdatedAt(maintenant);
        budget.setUpdatedBy(roleService.getRoleUtilisateur());
        if (budget.getCreatedAt() == null) {
            budget.setCreatedAt(maintenant);
            budget.setCreatedBy(roleService.getRoleUtilisateur());
        }
        budgetMissionRepository.save(budget);

        mission.setBudget(budget);
        mission.setAgentValidateurMg(agentValideur);
        mission.setDateValidationMg(maintenant);
        mission.setUpdatedBy(roleService.getRoleUtilisateur());
        mission.setDateMiseAJour(maintenant);
        mission.setFraisEstime(request.getMontantValide() != null ? request.getMontantValide().doubleValue() : mission.getFraisEstime());
        if (request.getSignatureFondeRecue() != null) {
            mission.setSignatureFondeRecue(Boolean.TRUE.equals(request.getSignatureFondeRecue()) ? 1 : 0);
        }
        missionPdfService.deleteIfExists(mission.getPdfFinalUri());
        mission.setPdfFinalUri(null);

        if (approuve) {
            mission.setStatutMission(MissionStatus.EN_ATTENTE_CAISSE);
            mission.setWorkflowPhase(PHASE_CAISSE);
            mission.setWorkflowStatut(WORKFLOW_STATUT_EN_ATTENTE);
            mission.setMotifRefusGlobal(null);
            notifierRole(mission, "CAISSE", "Mission à contrôler pour les fonds", "MISSION_TO_CAISSE");
        } else {
            mission.setStatutMission(MissionStatus.REFUSEE_MG);
            mission.setWorkflowPhase(PHASE_RH);
            mission.setWorkflowStatut(WORKFLOW_STATUT_REJETE);
            mission.setMotifRefusGlobal(request.getMotifRefus());
            try {
                notifierRole(mission, "RH", "Mission refusée par les Moyens Généraux", "MISSION_REFUSED_MG");
            } catch (Exception e) {
                logger.warn("Échec notification RH (non-bloquant): {}", e.getMessage(), e);
            }
            try {
                notifierRole(mission, "CHEF_SERVICE", "Mission refusée par les Moyens Généraux", "MISSION_REFUSED_MG");
            } catch (Exception e) {
                logger.warn("Échec notification Chef Service (non-bloquant): {}", e.getMessage(), e);
            }
        }

        validationWorkflowRepository.save(workflow);
        ordreMissionRepository.save(mission);

        journalService.enregistrerActionModification(
                idValideur,
                "GM_VALIDATION_WORKFLOW",
                "MG budgétisation mission=" + missionId,
                "Décision=" + decision,
                "127.0.0.1",
                "User-Agent"
        );

        return getMissionDetail(missionId).orElseThrow();
    }

    public MissionDetailDTO validerParRh(Long missionId, RhValidationRequest request) {
        GmOrdreMission mission = ordreMissionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("Mission introuvable"));

        verifierStatutMission(mission, List.of(MissionStatus.EN_ATTENTE_VALIDATION_RH),
                "La mission n'est pas disponible pour la validation RH");

        GmValidationEtape etapeRh = validationEtapeRepository.findByCodeEtape(ETAPE_RH_VALIDATION)
                .orElseThrow(() -> new IllegalStateException("Étape RH introuvable"));

        GmValidationWorkflow workflow = validationWorkflowRepository
                .findByOrdreMissionIdOrdreMissionAndEtapeCodeEtape(missionId, ETAPE_RH_VALIDATION)
                .orElseGet(() -> creerWorkflowInitial(mission, etapeRh));

        String decision = determinerDecision(request.getDecision(), WorkflowDecision.VALIDEE.name());
        boolean validee = WorkflowDecision.VALIDEE.matches(decision);
        Date maintenant = new Date();

        workflow.setDecision(decision);
        workflow.setStatutValidation(validee ? "VALIDEE" : "REJETEE");
        workflow.setCommentaires(request.getCommentaires());
        workflow.setMotifRefus(request.getMotifRefus());
        workflow.setDateValidation(maintenant);
        workflow.setUpdatedAt(maintenant);
        workflow.setUpdatedBy(roleService.getRoleUtilisateur());

        Long idValideur = roleService.getIdUtilisateurConnecte();
        GmAgent agentValideur = null;
        if (idValideur != null) {
            agentValideur = agentRepository.findById(idValideur).orElse(null);
            workflow.setAgentValideur(agentValideur);
        }

        if (request.getRessources() != null) {
            nettoyerRessourcesMission(missionId);
            if (!request.getRessources().isEmpty()) {
                persistMissionRessources(mission, request.getRessources());
            }
        }

        mission.setDateValidationRh(maintenant);
        mission.setAgentValidateurRh(agentValideur);
        mission.setUpdatedBy(roleService.getRoleUtilisateur());
        mission.setDateMiseAJour(maintenant);
        missionPdfService.deleteIfExists(mission.getPdfFinalUri());
        mission.setPdfFinalUri(null);

        if (validee) {
            // Génération PDF RH (non-bloquant)
            try {
                missionPdfService.deleteIfExists(mission.getPdfRhUri());
                String pdfRh = missionPdfService.generateDocument(mission, MissionPdfService.DocumentType.RH_VALIDATION);
                mission.setPdfRhUri(pdfRh);
                logger.info("PDF RH généré avec succès: {}", pdfRh);
            } catch (Exception e) {
                logger.error("Échec génération PDF RH (non-bloquant): {}", e.getMessage(), e);
                mission.setPdfRhUri(null);
            }
            mission.setStatutMission(MissionStatus.EN_ATTENTE_BUDGET_MG);
            mission.setWorkflowPhase(PHASE_MG);
            mission.setWorkflowStatut(WORKFLOW_STATUT_EN_ATTENTE);
            mission.setMotifRefusGlobal(null);
            // Notifications (non-bloquantes)
            try {
                notifierRole(mission, "MOYENS_GENERAUX", "Mission à budgéter", "MISSION_TO_BUDGET");
            } catch (Exception e) {
                logger.warn("Échec notification MG (non-bloquant): {}", e.getMessage(), e);
            }
            try {
                notifierRole(mission, "CHEF_SERVICE", "Mission acceptée par le RH", "MISSION_VALIDATED_RH");
            } catch (Exception e) {
                logger.warn("Échec notification Chef Service (non-bloquant): {}", e.getMessage(), e);
            }
        } else {
            try {
                missionPdfService.deleteIfExists(mission.getPdfRhUri());
            } catch (Exception e) {
                logger.warn("Erreur lors de la suppression du PDF RH: {}", e.getMessage());
            }
            mission.setPdfRhUri(null);
            mission.setStatutMission(MissionStatus.REFUSEE_RH);
            mission.setWorkflowPhase(PHASE_CHEF);
            mission.setWorkflowStatut(WORKFLOW_STATUT_REJETE);
            mission.setMotifRefusGlobal(request.getMotifRefus());
            try {
                notifierRole(mission, "CHEF_SERVICE", "Mission refusée par le RH", "MISSION_REFUSED_RH");
            } catch (Exception e) {
                logger.warn("Échec notification Chef Service (non-bloquant): {}", e.getMessage(), e);
            }
        }

        validationWorkflowRepository.save(workflow);
        ordreMissionRepository.save(mission);

        journalService.enregistrerActionModification(
                idValideur,
                "GM_VALIDATION_WORKFLOW",
                "RH validation mission=" + missionId,
                "Décision=" + decision,
                "127.0.0.1",
                "User-Agent"
        );

        return getMissionDetail(missionId).orElseThrow();
    }

    public MissionDetailDTO validerParCaisse(Long missionId, ValidationCaisseRequest request) {
        GmOrdreMission mission = ordreMissionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("Mission introuvable"));

        verifierStatutMission(mission, List.of(MissionStatus.EN_ATTENTE_CAISSE),
                "La mission n'est pas en attente de validation caisse");

        GmValidationEtape etapeCaisse = validationEtapeRepository.findByCodeEtape(ETAPE_CAISSE)
                .orElseThrow(() -> new IllegalStateException("Étape caisse introuvable"));

        GmValidationWorkflow workflow = validationWorkflowRepository
                .findByOrdreMissionIdOrdreMissionAndEtapeCodeEtape(missionId, ETAPE_CAISSE)
                .orElseGet(() -> creerWorkflowInitial(mission, etapeCaisse));

        boolean fondsDisponibles = Boolean.TRUE.equals(request.getFondsDisponible());
        String defaultDecision = fondsDisponibles ? WorkflowDecision.VALIDEE.name() : WorkflowDecision.REJETEE.name();
        String decision = determinerDecision(request.getDecision(), defaultDecision);
        boolean approuve = WorkflowDecision.VALIDEE.matches(decision);

        Date maintenant = new Date();

        workflow.setDecision(decision);
        workflow.setStatutValidation(approuve ? "VALIDEE" : "REJETEE");
        workflow.setFondsDisponible(fondsDisponibles ? 1 : 0);
        workflow.setReferencePiece(request.getReferencePiece());
        workflow.setMotifRefus(request.getMotifRefus());
        workflow.setDateValidation(maintenant);
        workflow.setUpdatedAt(maintenant);
        workflow.setUpdatedBy(roleService.getRoleUtilisateur());

        Long idValideur = roleService.getIdUtilisateurConnecte();
        GmAgent agentValideur = null;
        if (idValideur != null) {
            agentValideur = agentRepository.findById(idValideur).orElse(null);
            workflow.setAgentValideur(agentValideur);
        }

        if (request.getSignatureAgentComptableRecue() != null) {
            mission.setSignatureAgentComptableRecue(Boolean.TRUE.equals(request.getSignatureAgentComptableRecue()) ? 1 : 0);
        }

        mission.setAgentValidateurCaisse(agentValideur);
        mission.setDateValidationCaisse(maintenant);
        mission.setUpdatedBy(roleService.getRoleUtilisateur());
        mission.setDateMiseAJour(maintenant);
        missionPdfService.deleteIfExists(mission.getPdfFinalUri());
        mission.setPdfFinalUri(null);

        if (approuve) {
            mission.setStatutMission(MissionStatus.FONDS_CONFIRMES);
            mission.setWorkflowPhase(PHASE_MG);
            mission.setWorkflowStatut(WORKFLOW_STATUT_EN_ATTENTE);
            mission.setMotifRefusGlobal(null);
            notifierRole(mission, "MOYENS_GENERAUX", "Fonds confirmés par la caisse", "FONDS_CONFIRMES");
            notifierRole(mission, "RH", "Fonds confirmés, mission en attente de finalisation MG", "FONDS_CONFIRMES");
        } else {
            mission.setStatutMission(MissionStatus.REFUSEE_CAISSE);
            mission.setWorkflowPhase(PHASE_RH);
            mission.setWorkflowStatut(WORKFLOW_STATUT_REJETE);
            mission.setMotifRefusGlobal(request.getMotifRefus());
            notifierRole(mission, "MOYENS_GENERAUX", "Mission refusée par la caisse", "MISSION_REFUSED_CAISSE");
            notifierRole(mission, "RH", "Mission refusée par la caisse", "MISSION_REFUSED_CAISSE");
            notifierRole(mission, "CHEF_SERVICE", "Mission refusée par la caisse", "MISSION_REFUSED_CAISSE");
        }

        validationWorkflowRepository.save(workflow);
        ordreMissionRepository.save(mission);

        journalService.enregistrerActionModification(
                idValideur,
                "GM_VALIDATION_WORKFLOW",
                "Caisse avant :" + missionId,
                "Caisse après :" + decision,
                "127.0.0.1",
                "User-Agent"
        );

        return getMissionDetail(missionId).orElseThrow();
    }

    public MissionDetailDTO finaliserParMg(Long missionId, FinalisationMgRequest request) {
        GmOrdreMission mission = ordreMissionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("Mission introuvable"));

        verifierStatutMission(mission, List.of(MissionStatus.FONDS_CONFIRMES),
                "La mission n'est pas disponible pour la finalisation MG");

        GmValidationEtape etapeFinalisation = validationEtapeRepository.findByCodeEtape(ETAPE_FINALISATION)
                .orElseThrow(() -> new IllegalStateException("Étape de finalisation MG introuvable"));

        GmValidationWorkflow workflow = validationWorkflowRepository
                .findByOrdreMissionIdOrdreMissionAndEtapeCodeEtape(missionId, ETAPE_FINALISATION)
                .orElseGet(() -> creerWorkflowInitial(mission, etapeFinalisation));

        String decision = determinerDecision(request.getDecision(), WorkflowDecision.VALIDEE.name());
        boolean finalisee = WorkflowDecision.VALIDEE.name().equals(decision);
        Date maintenant = new Date();

        workflow.setDecision(decision);
        workflow.setStatutValidation(finalisee ? "VALIDEE" : "REJETEE");
        workflow.setCommentaires(request.getCommentaires());
        workflow.setMotifRefus(request.getMotifRefus());
        workflow.setDateValidation(maintenant);
        workflow.setUpdatedAt(maintenant);
        workflow.setUpdatedBy(roleService.getRoleUtilisateur());

        Long idValideur = roleService.getIdUtilisateurConnecte();
        if (idValideur != null) {
            GmAgent agent = agentRepository.findById(idValideur).orElse(null);
            workflow.setAgentValideur(agent);
        }

        mission.setStatutMission(finalisee ? MissionStatus.PRET_IMPRESSION : MissionStatus.ANNULEE);
        mission.setWorkflowPhase(finalisee ? PHASE_RH_FINAL : PHASE_CHEF);
        mission.setWorkflowStatut(finalisee ? WORKFLOW_STATUT_EN_ATTENTE : WORKFLOW_STATUT_REJETE);
        mission.setMotifRefusGlobal(finalisee ? null : request.getMotifRefus());
        mission.setDateMiseAJour(maintenant);
        mission.setUpdatedBy(roleService.getRoleUtilisateur());

        validationWorkflowRepository.save(workflow);
        ordreMissionRepository.save(mission);

        journalService.enregistrerActionModification(
                idValideur,
                "GM_VALIDATION_WORKFLOW",
                "MG finalisation mission=" + missionId,
                "Décision=" + decision,
                "127.0.0.1",
                "User-Agent"
        );

        if (finalisee) {
            notifierRole(mission, "RH", "Mission prête pour impression", "MISSION_READY_PRINT");
            notifierRole(mission, "CHEF_SERVICE", "Mission validée, ordre en cours d'impression", "MISSION_READY_PRINT");
        } else {
            notifierRole(mission, "CHEF_SERVICE", "Mission annulée par les Moyens Généraux", "MISSION_ANNULEE_MG");
            notifierRole(mission, "RH", "Mission annulée par les Moyens Généraux", "MISSION_ANNULEE_MG");
        }

        return getMissionDetail(missionId).orElseThrow();
    }

    public MissionDetailDTO cloturerParRh(Long missionId, ClotureRhRequest request) {
        GmOrdreMission mission = ordreMissionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("Mission introuvable"));

        verifierStatutMission(mission, List.of(MissionStatus.PRET_IMPRESSION),
                "La mission n'est pas disponible pour la clôture RH");

        GmValidationEtape etapeRh = validationEtapeRepository.findByCodeEtape(ETAPE_RH_IMPRESSION)
                .orElseThrow(() -> new IllegalStateException("Étape de clôture RH introuvable"));

        GmValidationWorkflow workflow = validationWorkflowRepository
                .findByOrdreMissionIdOrdreMissionAndEtapeCodeEtape(missionId, ETAPE_RH_IMPRESSION)
                .orElseGet(() -> creerWorkflowInitial(mission, etapeRh));

        String decision = determinerDecision(request.getDecision(), WorkflowDecision.VALIDEE.name());
        boolean cloturee = WorkflowDecision.VALIDEE.name().equals(decision);
        Date maintenant = new Date();

        workflow.setDecision(decision);
        workflow.setStatutValidation(cloturee ? "VALIDEE" : "REJETEE");
        workflow.setCommentaires(request.getCommentaires());
        workflow.setMotifRefus(request.getMotifRefus());
        workflow.setDateValidation(maintenant);
        workflow.setUpdatedAt(maintenant);
        workflow.setUpdatedBy(roleService.getRoleUtilisateur());

        Long idValideur = roleService.getIdUtilisateurConnecte();
        if (idValideur != null) {
            GmAgent agent = agentRepository.findById(idValideur).orElse(null);
            workflow.setAgentValideur(agent);
        }

        if (Boolean.TRUE.equals(request.getOrdreImprime())) {
            mission.setSignatureFondeRecue(1);
        }
        mission.setStatutMission(cloturee ? MissionStatus.TERMINEE : MissionStatus.ANNULEE);
        mission.setWorkflowPhase(cloturee ? PHASE_RH_FINAL : PHASE_CHEF);
        mission.setWorkflowStatut(cloturee ? WORKFLOW_STATUT_TERMINE : WORKFLOW_STATUT_REJETE);
        mission.setMotifRefusGlobal(cloturee ? null : request.getMotifRefus());
        mission.setDateMiseAJour(maintenant);
        mission.setUpdatedBy(roleService.getRoleUtilisateur());

        validationWorkflowRepository.save(workflow);
        ordreMissionRepository.save(mission);

        journalService.enregistrerActionModification(
                idValideur,
                "GM_VALIDATION_WORKFLOW",
                "RH clôture mission=" + missionId,
                "Décision=" + decision,
                "127.0.0.1",
                "User-Agent"
        );

        if (cloturee) {
            missionPdfService.deleteIfExists(mission.getPdfFinalUri());
            mission.setPdfFinalUri(missionPdfService.generateDocument(mission, MissionPdfService.DocumentType.FINAL_ORDER));
            notifierRole(mission, "CHEF_SERVICE", "Ordre de mission disponible", "MISSION_TERMINEE");
        } else {
            missionPdfService.deleteIfExists(mission.getPdfFinalUri());
            mission.setPdfFinalUri(null);
            notifierRole(mission, "CHEF_SERVICE", "Mission annulée lors de l'impression RH", "MISSION_ANNULEE_RH");
        }

        return getMissionDetail(missionId).orElseThrow();
    }

    private GmValidationWorkflow creerWorkflowInitial(GmOrdreMission mission, GmValidationEtape etape) {
        GmValidationWorkflow workflow = new GmValidationWorkflow();
        workflow.setOrdreMission(mission);
        workflow.setEtape(etape);
        workflow.setNiveauValidation(etape.getOrdreEtape());
        workflow.setOrdreValidation(etape.getOrdreEtape());
        workflow.setCreatedAt(new Date());
        workflow.setCreatedBy(roleService.getRoleUtilisateur());
        return workflow;
    }

    private String determinerDecision(String decisionRequise, String decisionParDefaut) {
        String value = (decisionRequise == null || decisionRequise.isBlank())
                ? decisionParDefaut
                : decisionRequise.trim().toUpperCase();
        return WorkflowDecision.normaliser(value);
    }

    public GmOrdreMission updateOrdreMission(Long id, GmOrdreMission ordreMissionDetails) {
        GmOrdreMission ordreMission = ordreMissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordre de mission non trouvé avec l'ID: " + id));

        // Enregistrer l'action de modification (avant/après)
        String donneesAvant = "Code: " + ordreMission.getCodeMission() + ", Objet: " + ordreMission.getObjetMission() + 
                             ", Début: " + ordreMission.getDateDebutMission() + ", Fin: " + ordreMission.getDateFinMission();
        
        // Mettre à jour les champs
        ordreMission.setCodeMission(ordreMissionDetails.getCodeMission());
        ordreMission.setObjetMission(ordreMissionDetails.getObjetMission());
        ordreMission.setDateDebutMission(ordreMissionDetails.getDateDebutMission());
        ordreMission.setDateFinMission(ordreMissionDetails.getDateFinMission());
        ordreMission.setLieuDepart(ordreMissionDetails.getLieuDepart());
        ordreMission.setLieuDestination(ordreMissionDetails.getLieuDestination());
        ordreMission.setMotifMission(ordreMissionDetails.getMotifMission());
        ordreMission.setFraisEstime(ordreMissionDetails.getFraisEstime());
        ordreMission.setStatutMission(ordreMissionDetails.getStatutMission());

        String donneesApres = "Code: " + ordreMission.getCodeMission() + ", Objet: " + ordreMission.getObjetMission() + 
                             ", Début: " + ordreMission.getDateDebutMission() + ", Fin: " + ordreMission.getDateFinMission();
        
        journalService.enregistrerActionModification(
            roleService.getIdUtilisateurConnecte(), 
            "GM_ORDRE_MISSION", 
            donneesAvant,
            donneesApres,
            "127.0.0.1", 
            "User-Agent"
        );
        
        return ordreMissionRepository.save(ordreMission);
    }

    public void deleteOrdreMission(Long id) {
        Optional<GmOrdreMission> ordreMission = ordreMissionRepository.findById(id);
        if (ordreMission.isPresent()) {
            // Enregistrer l'action de suppression
            GmOrdreMission mission = ordreMission.get();
            journalService.enregistrerActionSuppression(
                roleService.getIdUtilisateurConnecte(), 
                "GM_ORDRE_MISSION", 
                "Code: " + mission.getCodeMission() + ", Objet: " + mission.getObjetMission(), 
                "127.0.0.1", 
                "User-Agent"
            );
            
            ordreMissionRepository.deleteById(id);
        }
    }

    private String genererCodeMission(Long idOrdreMission) {
        return String.format("MIS-%05d", idOrdreMission);
    }

    private void persistMissionRessources(GmOrdreMission mission, List<MissionCreationRequest.MissionRessourceCreationDTO> ressources) {
        ressources.forEach(ressourceDTO -> {
            if (ressourceDTO.getRessourceId() == null) {
                return;
            }
            var ressource = ressourceRepository.findById(ressourceDTO.getRessourceId())
                    .orElseThrow(() -> new IllegalArgumentException("Ressource introuvable: " + ressourceDTO.getRessourceId()));

            GmMissionRessource association = new GmMissionRessource();
            association.setOrdreMission(mission);
            association.setRessource(ressource);
            association.setQuantite(ressourceDTO.getQuantite());
            association.setUnite(ressourceDTO.getUnite());
            association.setCommentaire(ressourceDTO.getCommentaire());
            association.setCreatedAt(new Date());
            association.setCreatedBy(roleService.getRoleUtilisateur());

            missionRessourceRepository.save(association);
        });
    }

    private void nettoyerRessourcesMission(Long missionId) {
        List<GmMissionRessource> existantes = missionRessourceRepository.findByOrdreMissionIdOrdreMission(missionId);
        if (!existantes.isEmpty()) {
            missionRessourceRepository.deleteAll(existantes);
        }
    }

    private void persistMissionEtapes(GmOrdreMission mission, MissionCreationRequest request) {
        if (request.getEtapes() == null || request.getEtapes().isEmpty()) {
            return;
        }

        request.getEtapes().forEach(etapeDTO -> {
            if (etapeDTO == null) {
                return;
            }

            GmMissionEtape etape = new GmMissionEtape();
            etape.setOrdreMission(mission);
            etape.setOrdrePassage(etapeDTO.getOrdrePassage());
            etape.setVilleDepartCode(etapeDTO.getVilleDepartCode());
            etape.setVilleArriveCode(etapeDTO.getVilleArriveCode());
            etape.setDateArrivee(etapeDTO.getDateArrivee());
            etape.setDateDepart(etapeDTO.getDateDepart());
            etape.setCommentaireEtape(etapeDTO.getObjectif());
            etape.setCreatedAt(new Date());
            etape.setCreatedBy(roleService.getRoleUtilisateur());

            missionEtapeRepository.save(etape);
        });
    }

    private void nettoyerEtapesMission(Long missionId) {
        List<GmMissionEtape> existantes = missionEtapeRepository.findByOrdreMissionIdOrdreMissionOrderByOrdrePassageAsc(missionId);
        if (!existantes.isEmpty()) {
            missionEtapeRepository.deleteAll(existantes);
        }
    }

    private void verifierStatutMission(GmOrdreMission mission, List<String> statutsAutorises, String messageErreur) {
        if (statutsAutorises == null || statutsAutorises.isEmpty()) {
            return;
        }
        String statutCourant = mission.getStatutMission();
        boolean autorise = statutsAutorises.stream()
                .anyMatch(code -> code.equalsIgnoreCase(statutCourant));
        if (!autorise) {
            throw new IllegalStateException(messageErreur + " (statut actuel: " + statutCourant + ")");
        }
    }

    private void notifierRole(GmOrdreMission mission, String role, String message, String type) {
        if (mission == null || role == null) {
            return;
        }

        GmMissionNotification notification = new GmMissionNotification();
        notification.setOrdreMission(mission);
        notification.setDestinataireRole(role);
        notification.setTypeNotification(type);
        notification.setMessageNotification(message);
        notification.setStatutNotification("NON_LU");
        notification.setCreatedAt(new Date());
        notification.setCreatedBy(roleService.getRoleUtilisateur());

        missionNotificationRepository.save(notification);
    }

    private List<String> statutsPourRole(String role) {
        if (role == null) {
            return List.of();
        }
        switch (role.toUpperCase()) {
            case "CHEF_SERVICE":
                return List.of(
                        MissionStatus.BROUILLON,
                        MissionStatus.EN_ATTENTE_VALIDATION_RH,
                        MissionStatus.REFUSEE_RH,
                        MissionStatus.EN_ATTENTE_BUDGET_MG,
                        MissionStatus.REFUSEE_MG,
                        MissionStatus.EN_ATTENTE_CAISSE,
                        MissionStatus.REFUSEE_CAISSE,
                        MissionStatus.FONDS_CONFIRMES,
                        MissionStatus.PRET_IMPRESSION,
                        MissionStatus.TERMINEE,
                        MissionStatus.ANNULEE
                );
            case "RH":
                return List.of(
                        MissionStatus.EN_ATTENTE_VALIDATION_RH,
                        MissionStatus.EN_ATTENTE_BUDGET_MG,
                        MissionStatus.EN_ATTENTE_CAISSE,
                        MissionStatus.FONDS_CONFIRMES,
                        MissionStatus.PRET_IMPRESSION,
                        MissionStatus.TERMINEE,
                        MissionStatus.ANNULEE,
                        MissionStatus.REFUSEE_RH,
                        MissionStatus.REFUSEE_MG,
                        MissionStatus.REFUSEE_CAISSE
                );
            case "MOYENS_GENERAUX":
                return List.of(
                        MissionStatus.EN_ATTENTE_BUDGET_MG,
                        MissionStatus.EN_ATTENTE_CAISSE,
                        MissionStatus.FONDS_CONFIRMES,
                        MissionStatus.PRET_IMPRESSION,
                        MissionStatus.REFUSEE_MG,
                        MissionStatus.REFUSEE_CAISSE,
                        MissionStatus.ANNULEE
                );
            case "CAISSE":
                return List.of(
                        MissionStatus.EN_ATTENTE_CAISSE,
                        MissionStatus.FONDS_CONFIRMES,
                        MissionStatus.REFUSEE_CAISSE,
                        MissionStatus.PRET_IMPRESSION,
                        MissionStatus.TERMINEE
                );
            case "GESTIONNAIRE":
            case "USER":
                return List.of(
                        MissionStatus.EN_ATTENTE_VALIDATION_RH,
                        MissionStatus.EN_ATTENTE_BUDGET_MG,
                        MissionStatus.EN_ATTENTE_CAISSE,
                        MissionStatus.REFUSEE_RH,
                        MissionStatus.REFUSEE_MG,
                        MissionStatus.REFUSEE_CAISSE
                );
            default:
                return List.of();
        }
    }
}