package com.example.Gestion_mission.controller;

import com.example.Gestion_mission.annotation.JournaliserAction;
import com.example.Gestion_mission.annotation.RoleAutorise;
import com.example.Gestion_mission.dto.MissionParticipantDTO;
import com.example.Gestion_mission.model.GmMissionAgent;
import com.example.Gestion_mission.service.MissionAgentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/missions/{missionId}/participants")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MissionAgentController {

    private final MissionAgentService missionAgentService;

    public MissionAgentController(MissionAgentService missionAgentService) {
        this.missionAgentService = missionAgentService;
    }

    @GetMapping
    @JournaliserAction(entite = "GM_MISSION_AGENT", action = "READ")
    public ResponseEntity<List<MissionParticipantDTO>> lister(@PathVariable Long missionId) {
        List<MissionParticipantDTO> participants = missionAgentService.listerParticipants(missionId).stream()
                .map(this::mapParticipant)
                .collect(Collectors.toList());
        return ResponseEntity.ok(participants);
    }

    @PostMapping
    @RoleAutorise(roles = {"ADMIN", "GESTIONNAIRE"}, peutModifier = true)
    @JournaliserAction(entite = "GM_MISSION_AGENT", action = "CREATE")
    public ResponseEntity<MissionParticipantDTO> ajouter(@PathVariable Long missionId,
                                                         @RequestBody MissionAgentRequest request) {
        GmMissionAgent participant = missionAgentService.ajouterParticipant(missionId, request.agentId(), request.roleMission());
        return ResponseEntity.ok(mapParticipant(participant));
    }

    @DeleteMapping("/{missionAgentId}")
    @RoleAutorise(roles = {"ADMIN", "GESTIONNAIRE"}, peutSupprimer = true)
    @JournaliserAction(entite = "GM_MISSION_AGENT", action = "DELETE")
    public ResponseEntity<Void> supprimer(@PathVariable Long missionAgentId) {
        missionAgentService.supprimerParticipant(missionAgentId);
        return ResponseEntity.ok().build();
    }

    public record MissionAgentRequest(Long agentId, String roleMission) {
    }

    private MissionParticipantDTO mapParticipant(GmMissionAgent missionAgent) {
        return new MissionParticipantDTO(
                missionAgent.getIdMissionAgent(),
                missionAgent.getAgent().getIdAgent(),
                missionAgent.getAgent().getMatriculeAgent(),
                missionAgent.getAgent().getNomAgent(),
                missionAgent.getAgent().getPrenomAgent(),
                missionAgent.getRoleMission()
        );
    }
}
