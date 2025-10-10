package com.example.Gestion_mission.controller;

import com.example.Gestion_mission.annotation.JournaliserAction;
import com.example.Gestion_mission.annotation.RoleAutorise;
import com.example.Gestion_mission.dto.MissionCreationRequest;
import com.example.Gestion_mission.dto.MissionDetailDTO;
import com.example.Gestion_mission.dto.OrdreMissionDTO;
import com.example.Gestion_mission.model.GmOrdreMission;
import com.example.Gestion_mission.service.GmOrdreMissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<MissionDetailDTO> getOrdreMissionById(@PathVariable Long id) {
        return ordreMissionService.getMissionDetail(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @RoleAutorise(roles = {"GESTIONNAIRE", "ADMIN"}, peutSupprimer = false, peutModifier = false)
    @JournaliserAction(entite = "GM_ORDRE_MISSION", action = "CREATE")
    public ResponseEntity<GmOrdreMission> createOrdreMission(@RequestBody MissionCreationRequest request) {
        GmOrdreMission createdOrdreMission = ordreMissionService.createOrdreMission(request);
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