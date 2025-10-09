package com.example.Gestion_mission.service;

import com.example.Gestion_mission.model.GmAgent;
import com.example.Gestion_mission.model.GmOrdreMission;
import com.example.Gestion_mission.model.GmValidationEtape;
import com.example.Gestion_mission.model.GmValidationWorkflow;
import com.example.Gestion_mission.repository.GmAgentRepository;
import com.example.Gestion_mission.repository.GmOrdreMissionRepository;
import com.example.Gestion_mission.repository.GmValidationEtapeRepository;
import com.example.Gestion_mission.repository.GmValidationWorkflowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class ValidationWorkflowService {

    private static final Logger log = LoggerFactory.getLogger(ValidationWorkflowService.class);

    private final GmValidationWorkflowRepository workflowRepository;
    private final GmValidationEtapeRepository etapeRepository;
    private final GmOrdreMissionRepository ordreMissionRepository;
    private final GmAgentRepository agentRepository;
    private final JournalUtilisateurService journalService;
    private final RoleService roleService;

    public ValidationWorkflowService(GmValidationWorkflowRepository workflowRepository,
                                     GmValidationEtapeRepository etapeRepository,
                                     GmOrdreMissionRepository ordreMissionRepository,
                                     GmAgentRepository agentRepository,
                                     JournalUtilisateurService journalService,
                                     RoleService roleService) {
        this.workflowRepository = workflowRepository;
        this.etapeRepository = etapeRepository;
        this.ordreMissionRepository = ordreMissionRepository;
        this.agentRepository = agentRepository;
        this.journalService = journalService;
        this.roleService = roleService;
    }

    @Transactional(readOnly = true)
    public List<GmValidationWorkflow> listerParMission(Long idOrdreMission) {
        log.info("Lecture du workflow pour la mission {}", idOrdreMission);
        journalService.enregistrerActionConsultation(roleService.getIdUtilisateurConnecte(), "GM_VALIDATION_WORKFLOW", "127.0.0.1", "SpringBoot");
        return workflowRepository.findByOrdreMissionIdOrdreMissionOrderByOrdreValidationAsc(idOrdreMission);
    }

    @Transactional
    public GmValidationWorkflow enregistrerDecision(Long idOrdreMission, String codeEtape, boolean approuve, String motifRefus, Double montantValide, boolean fondsDisponible, String referencePiece) {
        GmOrdreMission mission = ordreMissionRepository.findById(idOrdreMission)
                .orElseThrow(() -> new IllegalArgumentException("Mission introuvable: " + idOrdreMission));

        GmValidationEtape etape = etapeRepository.findByCodeEtape(codeEtape)
                .orElseThrow(() -> new IllegalArgumentException("Étape inconnue: " + codeEtape));

        GmValidationWorkflow workflow = workflowRepository
                .findByOrdreMissionIdOrdreMissionAndEtapeCodeEtape(idOrdreMission, codeEtape)
                .orElseGet(GmValidationWorkflow::new);

        workflow.setOrdreMission(mission);
        workflow.setEtape(etape);
        workflow.setStatutValidation(approuve ? "APPROUVE" : "REFUSE");
        workflow.setDecision(approuve ? "APPROUVE" : "REFUSE");
        workflow.setMotifRefus(approuve ? null : motifRefus);
        workflow.setDateValidation(new Date());
        workflow.setMontantValide(montantValide != null ? BigDecimal.valueOf(montantValide) : null);
        workflow.setFondsDisponible(fondsDisponible ? 1 : 0);
        workflow.setReferencePiece(referencePiece);

        Long idAgent = roleService.getIdUtilisateurConnecte();
        if (idAgent != null) {
            GmAgent agent = agentRepository.findById(idAgent)
                    .orElseThrow(() -> new IllegalStateException("Agent connecté introuvable"));
            workflow.setAgentValideur(agent);
        }

        log.info("Décision {} enregistrée pour l'étape {} de la mission {}", workflow.getDecision(), codeEtape, idOrdreMission);
        journalService.enregistrerActionModification(roleService.getIdUtilisateurConnecte(), "GM_VALIDATION_WORKFLOW",
                "Ancienne décision", "Mission=" + idOrdreMission + ",Étape=" + codeEtape + ",Décision=" + workflow.getDecision(),
                "127.0.0.1", "SpringBoot");

        return workflowRepository.save(workflow);
    }
}
