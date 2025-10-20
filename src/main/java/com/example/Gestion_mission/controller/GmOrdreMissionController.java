package com.example.Gestion_mission.controller;

import com.example.Gestion_mission.annotation.JournaliserAction;
import com.example.Gestion_mission.annotation.RoleAutorise;
import com.example.Gestion_mission.dto.ClotureRhRequest;
import com.example.Gestion_mission.dto.FinalisationMgRequest;
import com.example.Gestion_mission.dto.MissionCreationRequest;
import com.example.Gestion_mission.dto.MissionDetailDTO;
import com.example.Gestion_mission.dto.OrdreMissionDTO;
import com.example.Gestion_mission.dto.RhValidationRequest;
import com.example.Gestion_mission.dto.ValidationCaisseRequest;
import com.example.Gestion_mission.dto.ValidationMgRequest;
import com.example.Gestion_mission.model.GmOrdreMission;
import com.example.Gestion_mission.service.GmOrdreMissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

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

    @GetMapping("/statuts")
    @JournaliserAction(entite = "GM_ORDRE_MISSION", action = "READ")
    public ResponseEntity<List<OrdreMissionDTO>> getByStatuts(@RequestParam(name = "codes") List<String> codes) {
        return ResponseEntity.ok(ordreMissionService.listerParStatut(codes));
    }

    @GetMapping("/role")
    @JournaliserAction(entite = "GM_ORDRE_MISSION", action = "READ")
    public ResponseEntity<List<OrdreMissionDTO>> getMissionsPourRoleCourant() {
        return ResponseEntity.ok(ordreMissionService.getMissionsPourRoleCourant());
    }

    @GetMapping("/mine")
    @JournaliserAction(entite = "GM_ORDRE_MISSION", action = "READ")
    public ResponseEntity<List<OrdreMissionDTO>> getMissionsPourUtilisateurCourant() {
        return ResponseEntity.ok(ordreMissionService.getMissionsCreeesParUtilisateur());
    }

    @GetMapping("/{id}")
    @JournaliserAction(entite = "GM_ORDRE_MISSION", action = "READ")
    public ResponseEntity<MissionDetailDTO> getOrdreMissionById(@PathVariable Long id) {
        return ordreMissionService.getMissionDetail(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @RoleAutorise(roles = {"CHEF_SERVICE", "ADMIN"}, peutSupprimer = false, peutModifier = false)
    @JournaliserAction(entite = "GM_ORDRE_MISSION", action = "CREATE")
    public ResponseEntity<GmOrdreMission> createOrdreMission(@RequestBody MissionCreationRequest request) {
        GmOrdreMission createdOrdreMission = ordreMissionService.createOrdreMission(request);
        return ResponseEntity.ok(createdOrdreMission);
    }

    @PostMapping("/{id}/validation-rh")
    @RoleAutorise(roles = {"RH", "ADMIN"}, peutModifier = true, peutSupprimer = false)
    @JournaliserAction(entite = "GM_ORDRE_MISSION", action = "UPDATE")
    public ResponseEntity<MissionDetailDTO> validerParRh(@PathVariable Long id,
                                                         @RequestBody @Valid RhValidationRequest request) {
        return ResponseEntity.ok(ordreMissionService.validerParRh(id, request));
    }

    @PostMapping("/{id}/validation-mg")
    @RoleAutorise(roles = {"MOYENS_GENERAUX", "ADMIN"}, peutModifier = true, peutSupprimer = false)
    @JournaliserAction(entite = "GM_ORDRE_MISSION", action = "UPDATE")
    public ResponseEntity<MissionDetailDTO> validerParMg(@PathVariable Long id,
                                                         @RequestBody @Valid ValidationMgRequest request) {
        return ResponseEntity.ok(ordreMissionService.validerParMg(id, request));
    }

    @PostMapping("/{id}/validation-caisse")
    @RoleAutorise(roles = {"CAISSE", "ADMIN"}, peutModifier = true, peutSupprimer = false)
    @JournaliserAction(entite = "GM_ORDRE_MISSION", action = "UPDATE")
    public ResponseEntity<MissionDetailDTO> validerParCaisse(@PathVariable Long id,
                                                             @RequestBody @Valid ValidationCaisseRequest request) {
        return ResponseEntity.ok(ordreMissionService.validerParCaisse(id, request));
    }

    @PostMapping("/{id}/finalisation-mg")
    @RoleAutorise(roles = {"MOYENS_GENERAUX", "ADMIN"}, peutModifier = true, peutSupprimer = false)
    @JournaliserAction(entite = "GM_ORDRE_MISSION", action = "UPDATE")
    public ResponseEntity<MissionDetailDTO> finaliserParMg(@PathVariable Long id,
                                                           @RequestBody @Valid FinalisationMgRequest request) {
        return ResponseEntity.ok(ordreMissionService.finaliserParMg(id, request));
    }

    @PostMapping("/{id}/cloture-rh")
    @RoleAutorise(roles = {"RH", "ADMIN"}, peutModifier = true, peutSupprimer = false)
    @JournaliserAction(entite = "GM_ORDRE_MISSION", action = "UPDATE")
    public ResponseEntity<MissionDetailDTO> cloturerParRh(@PathVariable Long id,
                                                          @RequestBody @Valid ClotureRhRequest request) {
        return ResponseEntity.ok(ordreMissionService.cloturerParRh(id, request));
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