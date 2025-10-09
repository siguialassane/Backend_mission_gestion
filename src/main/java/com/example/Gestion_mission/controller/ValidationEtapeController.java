package com.example.Gestion_mission.controller;

import com.example.Gestion_mission.annotation.JournaliserAction;
import com.example.Gestion_mission.model.GmValidationEtape;
import com.example.Gestion_mission.service.ValidationEtapeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workflow/etapes")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ValidationEtapeController {

    private static final Logger log = LoggerFactory.getLogger(ValidationEtapeController.class);

    private final ValidationEtapeService validationEtapeService;

    public ValidationEtapeController(ValidationEtapeService validationEtapeService) {
        this.validationEtapeService = validationEtapeService;
    }

    @GetMapping
    @JournaliserAction(entite = "GM_VALIDATION_ETAPE", action = "READ")
    public ResponseEntity<List<GmValidationEtape>> listerEtapes() {
        log.debug("API - Lecture du référentiel d'étapes de validation");
        return ResponseEntity.ok(validationEtapeService.listerParOrdre());
    }
}
