package com.example.Gestion_mission.controller;

import com.example.Gestion_mission.annotation.JournaliserAction;
import com.example.Gestion_mission.annotation.RoleAutorise;
import com.example.Gestion_mission.model.GmFraisMission;
import com.example.Gestion_mission.service.GmFraisMissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/frais")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GmFraisMissionController {

    @Autowired
    private GmFraisMissionService fraisMissionService;

    @GetMapping
    @JournaliserAction(entite = "GM_FRAIS_MISSION", action = "READ")
    public ResponseEntity<List<GmFraisMission>> getAllFraisMission() {
        List<GmFraisMission> fraisMission = fraisMissionService.getAllFraisMission();
        return ResponseEntity.ok(fraisMission);
    }

    @GetMapping("/mission/{idOrdreMission}")
    @JournaliserAction(entite = "GM_FRAIS_MISSION", action = "READ")
    public ResponseEntity<List<GmFraisMission>> getFraisMissionByOrdreMission(@PathVariable Long idOrdreMission) {
        List<GmFraisMission> fraisMission = fraisMissionService.getFraisMissionByOrdreMission(idOrdreMission);
        return ResponseEntity.ok(fraisMission);
    }

    @GetMapping("/{id}")
    @JournaliserAction(entite = "GM_FRAIS_MISSION", action = "READ")
    public ResponseEntity<Optional<GmFraisMission>> getFraisMissionById(@PathVariable Long id) {
        Optional<GmFraisMission> fraisMission = fraisMissionService.getFraisMissionById(id);
        return ResponseEntity.ok(fraisMission);
    }

    @PostMapping
    @RoleAutorise(roles = {"GESTIONNAIRE", "ADMIN"}, peutSupprimer = false, peutModifier = false)
    @JournaliserAction(entite = "GM_FRAIS_MISSION", action = "CREATE")
    public ResponseEntity<GmFraisMission> createFraisMission(@RequestBody GmFraisMission fraisMission) {
        GmFraisMission createdFraisMission = fraisMissionService.createFraisMission(fraisMission);
        return ResponseEntity.ok(createdFraisMission);
    }

    @PutMapping("/{id}")
    @RoleAutorise(roles = {"ADMIN"}, peutSupprimer = false, peutModifier = true)
    @JournaliserAction(entite = "GM_FRAIS_MISSION", action = "UPDATE")
    public ResponseEntity<GmFraisMission> updateFraisMission(@PathVariable Long id, @RequestBody GmFraisMission fraisMissionDetails) {
        GmFraisMission updatedFraisMission = fraisMissionService.updateFraisMission(id, fraisMissionDetails);
        return ResponseEntity.ok(updatedFraisMission);
    }

    @DeleteMapping("/{id}")
    @RoleAutorise(roles = {"ADMIN"}, peutSupprimer = true, peutModifier = false)
    @JournaliserAction(entite = "GM_FRAIS_MISSION", action = "DELETE")
    public ResponseEntity<?> deleteFraisMission(@PathVariable Long id) {
        fraisMissionService.deleteFraisMission(id);
        return ResponseEntity.ok().build();
    }
}