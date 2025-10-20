package com.example.Gestion_mission.controller;

import com.example.Gestion_mission.annotation.RoleAutorise;
import com.example.Gestion_mission.dto.MissionNotificationDTO;
import com.example.Gestion_mission.service.MissionNotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/missions/notifications")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MissionNotificationController {

    private final MissionNotificationService notificationService;

    public MissionNotificationController(MissionNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    @RoleAutorise(roles = {"ADMIN", "CHEF_SERVICE", "RH", "MOYENS_GENERAUX", "CAISSE"},
            peutModifier = false,
            peutSupprimer = false)
    public ResponseEntity<List<MissionNotificationDTO>> lister() {
        return ResponseEntity.ok(notificationService.listerPourUtilisateurCourant());
    }

    @PostMapping("/{id}/read")
    @RoleAutorise(roles = {"ADMIN", "CHEF_SERVICE", "RH", "MOYENS_GENERAUX", "CAISSE"},
            peutModifier = true,
            peutSupprimer = false)
    public ResponseEntity<MissionNotificationDTO> marquerCommeLu(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.marquerCommeLue(id));
    }

    @PostMapping("/read-all")
    @RoleAutorise(roles = {"ADMIN", "CHEF_SERVICE", "RH", "MOYENS_GENERAUX", "CAISSE"},
            peutModifier = true,
            peutSupprimer = false)
    public ResponseEntity<Void> marquerToutCommeLu() {
        notificationService.marquerToutCommeLu();
        return ResponseEntity.noContent().build();
    }
}
