package com.example.Gestion_mission.controller;

import com.example.Gestion_mission.annotation.JournaliserAction;
import com.example.Gestion_mission.annotation.RoleAutorise;
import com.example.Gestion_mission.dto.OrdreMissionDTO;
import com.example.Gestion_mission.model.GmOrdreMission;
import com.example.Gestion_mission.service.GmOrdreMissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/missions")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GmOrdreMissionController {

    @Autowired
    private GmOrdreMissionService ordreMissionService;

    @GetMapping
    @JournaliserAction(entite = "GM_ORDRE_MISSION", action = "READ")
    public ResponseEntity<List<OrdreMissionDTO>> getAllOrdresMission() {
        List<OrdreMissionDTO> ordresMission = ordreMissionService.getAllOrdresMission();
        return ResponseEntity.ok(ordresMission);
    }

    @GetMapping("/{id}")
    @JournaliserAction(entite = "GM_ORDRE_MISSION", action = "READ")
    public ResponseEntity<Optional<GmOrdreMission>> getOrdreMissionById(@PathVariable Long id) {
        Optional<GmOrdreMission> ordreMission = ordreMissionService.getOrdreMissionById(id);
        return ResponseEntity.ok(ordreMission);
    }

    @PostMapping
    @RoleAutorise(roles = {"GESTIONNAIRE", "ADMIN"}, peutSupprimer = false, peutModifier = false)
    @JournaliserAction(entite = "GM_ORDRE_MISSION", action = "CREATE")
    public ResponseEntity<GmOrdreMission> createOrdreMission(@RequestBody GmOrdreMission ordreMission) {
        GmOrdreMission createdOrdreMission = ordreMissionService.createOrdreMission(ordreMission);
        return ResponseEntity.ok(createdOrdreMission);
    }

    @PutMapping("/{id}")
    @RoleAutorise(roles = {"ADMIN"}, peutSupprimer = false, peutModifier = true)
    @JournaliserAction(entite = "GM_ORDRE_MISSION", action = "UPDATE")
    public ResponseEntity<GmOrdreMission> updateOrdreMission(@PathVariable Long id, @RequestBody GmOrdreMission ordreMissionDetails) {
        GmOrdreMission updatedOrdreMission = ordreMissionService.updateOrdreMission(id, ordreMissionDetails);
        return ResponseEntity.ok(updatedOrdreMission);
    }

    @DeleteMapping("/{id}")
    @RoleAutorise(roles = {"ADMIN"}, peutSupprimer = true, peutModifier = false)
    @JournaliserAction(entite = "GM_ORDRE_MISSION", action = "DELETE")
    public ResponseEntity<?> deleteOrdreMission(@PathVariable Long id) {
        ordreMissionService.deleteOrdreMission(id);
        return ResponseEntity.ok().build();
    }
}