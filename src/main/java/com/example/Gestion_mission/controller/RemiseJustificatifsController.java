package com.example.Gestion_mission.controller;

import com.example.Gestion_mission.annotation.JournaliserAction;
import com.example.Gestion_mission.annotation.RoleAutorise;
import com.example.Gestion_mission.model.GmRemiseJustificatifs;
import com.example.Gestion_mission.service.RemiseJustificatifsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions/{missionId}/justificatifs")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RemiseJustificatifsController {

    private static final Logger log = LoggerFactory.getLogger(RemiseJustificatifsController.class);

    private final RemiseJustificatifsService remiseJustificatifsService;

    public RemiseJustificatifsController(RemiseJustificatifsService remiseJustificatifsService) {
        this.remiseJustificatifsService = remiseJustificatifsService;
    }

    @GetMapping
    @JournaliserAction(entite = "GM_REMISE_JUSTIFICATIFS", action = "READ")
    public ResponseEntity<GmRemiseJustificatifs> consulter(@PathVariable Long missionId) {
        log.debug("API - Consultation des justificatifs pour la mission {}", missionId);
        return ResponseEntity.ok(remiseJustificatifsService.consulterRemise(missionId));
    }

    @PostMapping
    @RoleAutorise(roles = {"GESTIONNAIRE", "ADMIN"}, peutModifier = true)
    @JournaliserAction(entite = "GM_REMISE_JUSTIFICATIFS", action = "UPDATE")
    public ResponseEntity<GmRemiseJustificatifs> enregistrer(@PathVariable Long missionId,
                                                             @RequestBody RemiseRequest request) {
        log.info("API - Mise à jour des justificatifs de la mission {}", missionId);
        GmRemiseJustificatifs remise = remiseJustificatifsService.enregistrerRemise(
                missionId,
                request.recuRapport(),
                request.recuFacture(),
                request.recuOrdre(),
                request.idAgentRecepteur(),
                request.observations()
        );
        return ResponseEntity.ok(remise);
    }

    public record RemiseRequest(boolean recuRapport,
                                boolean recuFacture,
                                boolean recuOrdre,
                                Long idAgentRecepteur,
                                String observations) {
    }
}
