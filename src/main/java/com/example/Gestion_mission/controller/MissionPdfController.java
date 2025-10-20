package com.example.Gestion_mission.controller;

import com.example.Gestion_mission.annotation.RoleAutorise;
import com.example.Gestion_mission.model.GmOrdreMission;
import com.example.Gestion_mission.repository.GmOrdreMissionRepository;
import com.example.Gestion_mission.service.MissionPdfService;
import com.example.Gestion_mission.service.MissionPdfService.DocumentType;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/missions")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MissionPdfController {

    private final MissionPdfService missionPdfService;
    private final GmOrdreMissionRepository ordreMissionRepository;

    public MissionPdfController(MissionPdfService missionPdfService,
                                GmOrdreMissionRepository ordreMissionRepository) {
        this.missionPdfService = missionPdfService;
        this.ordreMissionRepository = ordreMissionRepository;
    }

    @GetMapping("/{id}/pdf/{type}")
    @RoleAutorise(roles = {"ADMIN", "CHEF_SERVICE", "RH", "MOYENS_GENERAUX", "CAISSE"},
                  peutModifier = false,
                  peutSupprimer = false)
    public ResponseEntity<Resource> telecharger(@PathVariable Long id, @PathVariable String type) {
        GmOrdreMission mission = ordreMissionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mission introuvable"));

        DocumentType documentType = DocumentType.fromCode(type);
        String fileName = switch (documentType) {
            case CHEF_DEMANDE -> mission.getPdfChefUri();
            case RH_VALIDATION -> mission.getPdfRhUri();
            case FINAL_ORDER -> mission.getPdfFinalUri();
        };

        Resource resource = missionPdfService.loadAsResource(fileName);
        if (resource == null || !resource.exists()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PDF non disponible pour cette mission");
        }

        String downloadName = (mission.getCodeMission() != null ? mission.getCodeMission() : "mission")
                + "-" + documentType.getFileSuffix() + ".pdf";

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + downloadName + "\"")
                .body(resource);
    }
}
