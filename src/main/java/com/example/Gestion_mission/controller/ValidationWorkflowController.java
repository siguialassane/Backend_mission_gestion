package com.example.Gestion_mission.controller;

import com.example.Gestion_mission.annotation.JournaliserAction;
import com.example.Gestion_mission.annotation.RoleAutorise;
import com.example.Gestion_mission.model.GmValidationWorkflow;
import com.example.Gestion_mission.service.ValidationWorkflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/missions/{missionId}/workflow")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ValidationWorkflowController {

    private static final Logger log = LoggerFactory.getLogger(ValidationWorkflowController.class);

    private final ValidationWorkflowService validationWorkflowService;

    public ValidationWorkflowController(ValidationWorkflowService validationWorkflowService) {
        this.validationWorkflowService = validationWorkflowService;
    }

    @GetMapping
    @JournaliserAction(entite = "GM_VALIDATION_WORKFLOW", action = "READ")
    public ResponseEntity<List<GmValidationWorkflow>> consulterWorkflow(@PathVariable Long missionId) {
        log.debug("API - Consultation du workflow pour la mission {}", missionId);
        return ResponseEntity.ok(validationWorkflowService.listerParMission(missionId));
    }

    @PostMapping
    @RoleAutorise(roles = {"GESTIONNAIRE", "ADMIN"}, peutModifier = true)
    @JournaliserAction(entite = "GM_VALIDATION_WORKFLOW", action = "UPDATE")
    public ResponseEntity<GmValidationWorkflow> enregistrerDecision(@PathVariable Long missionId,
                                                                    @RequestBody ValidationDecisionRequest request) {
        log.info("API - Décision {} pour l'étape {} de la mission {}", request.approuve(), request.codeEtape(), missionId);
        GmValidationWorkflow workflow = validationWorkflowService.enregistrerDecision(
                missionId,
                request.codeEtape(),
                request.approuve(),
                request.motifRefus(),
                request.montantValide(),
                request.fondsDisponible(),
                request.referencePiece()
        );
        return ResponseEntity.ok(workflow);
    }

    public record ValidationDecisionRequest(String codeEtape,
                                            Boolean approuve,
                                            String motifRefus,
                                            Double montantValide,
                                            Boolean fondsDisponible,
                                            String referencePiece) {
        public Boolean approuve() {
            return approuve != null && approuve;
        }

        public Boolean fondsDisponible() {
            return fondsDisponible != null && fondsDisponible;
        }
    }
}
