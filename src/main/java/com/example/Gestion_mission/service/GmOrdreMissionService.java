package com.example.Gestion_mission.service;

import com.example.Gestion_mission.dto.MissionCreationRequest;
import com.example.Gestion_mission.dto.MissionDetailDTO;
import com.example.Gestion_mission.dto.OrdreMissionDTO;
import com.example.Gestion_mission.mapper.MissionDetailMapper;
import com.example.Gestion_mission.mapper.OrdreMissionMapper;
import com.example.Gestion_mission.model.GmOrdreMission;
import com.example.Gestion_mission.model.GmRemiseJustificatifs;
import com.example.Gestion_mission.repository.GmMissionEtapeRepository;
import com.example.Gestion_mission.repository.GmMissionRessourceRepository;
import com.example.Gestion_mission.repository.GmOrdreMissionRepository;
import com.example.Gestion_mission.repository.GmRemiseJustificatifsRepository;
import com.example.Gestion_mission.repository.GmValidationWorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
                    return missionDetailMapper.toDetailDTO(mission, ressources, etapes, workflow, justificatifs);
                });
    }

    public GmOrdreMission createOrdreMission(MissionCreationRequest request) {
        if (request.getDateDebutMission() == null || request.getDateFinMission() == null) {
            throw new IllegalArgumentException("Les dates de début et fin sont obligatoires");
        }
        if (request.getDateFinMission().before(request.getDateDebutMission())) {
            throw new IllegalArgumentException("La date de fin doit être postérieure à la date de début");
        }

        GmOrdreMission mission = new GmOrdreMission();
        mission.setObjetMission(request.getObjetMission());
        mission.setDateDebutMission(request.getDateDebutMission());
        mission.setDateFinMission(request.getDateFinMission());
        mission.setLieuDepart(request.getLieuDepart());
        mission.setLieuDestination(request.getLieuDestination());
        mission.setMotifMission(request.getMotifMission());
        mission.setStatutMission("BROUILLON");
        mission.setDateCreation(new Date());
        mission.setDateMiseAJour(new Date());
        mission.setCreatedBy(roleService.getRoleUtilisateur());
        mission.setIdAgentCreateur(roleService.getIdUtilisateurConnecte());

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

        return saved;
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
}