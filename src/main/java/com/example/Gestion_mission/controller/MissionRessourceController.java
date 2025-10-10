package com.example.Gestion_mission.controller;

import com.example.Gestion_mission.annotation.JournaliserAction;
import com.example.Gestion_mission.annotation.RoleAutorise;
import com.example.Gestion_mission.dto.MissionRessourceDTO;
import com.example.Gestion_mission.model.GmMissionRessource;
import com.example.Gestion_mission.service.MissionRessourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/missions/{missionId}/ressources")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MissionRessourceController {

    private static final Logger log = LoggerFactory.getLogger(MissionRessourceController.class);

    private final MissionRessourceService missionRessourceService;

    public MissionRessourceController(MissionRessourceService missionRessourceService) {
        this.missionRessourceService = missionRessourceService;
    }

    @GetMapping
    @JournaliserAction(entite = "GM_MISSION_RESSOURCE", action = "READ")
    public ResponseEntity<List<MissionRessourceDTO>> lister(@PathVariable Long missionId) {
        log.debug("API - Consultation des ressources pour la mission {}", missionId);
        List<MissionRessourceDTO> ressources = missionRessourceService.listerRessourcesMission(missionId).stream()
                .map(this::mapRessource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ressources);
    }

    @PostMapping
    @RoleAutorise(roles = {"GESTIONNAIRE", "ADMIN"}, peutModifier = true)
    @JournaliserAction(entite = "GM_MISSION_RESSOURCE", action = "CREATE")
    public ResponseEntity<MissionRessourceDTO> ajouter(@PathVariable Long missionId,
                                                     @RequestBody MissionRessourceRequest request) {
        log.info("API - Ajout d'une ressource {} à la mission {}", request.idRessource(), missionId);
        GmMissionRessource ressource = missionRessourceService.ajouterRessource(
                missionId,
                request.idRessource(),
                request.quantite(),
                request.unite(),
                request.commentaire()
        );
        return ResponseEntity.ok(mapRessource(ressource));
    }

    @DeleteMapping("/{missionRessourceId}")
    @RoleAutorise(roles = {"ADMIN"}, peutSupprimer = true)
    @JournaliserAction(entite = "GM_MISSION_RESSOURCE", action = "DELETE")
    public ResponseEntity<Void> supprimer(@PathVariable Long missionId, @PathVariable Long missionRessourceId) {
        log.warn("API - Suppression de la ressource {} de la mission {}", missionRessourceId, missionId);
        missionRessourceService.supprimerRessource(missionRessourceId);
        return ResponseEntity.ok().build();
    }

    public record MissionRessourceRequest(Long idRessource, Integer quantite, String unite, String commentaire) {
    }

    private MissionRessourceDTO mapRessource(GmMissionRessource entity) {
        MissionRessourceDTO dto = new MissionRessourceDTO();
        dto.setIdMissionRessource(entity.getIdMissionRessource());
        dto.setQuantite(entity.getQuantite());
        dto.setUnite(entity.getUnite());
        dto.setCommentaire(entity.getCommentaire());
        MissionRessourceDTO.RessourceDTO ressourceDTO = new MissionRessourceDTO.RessourceDTO();
        if (entity.getRessource() != null) {
            ressourceDTO.setIdRessource(entity.getRessource().getIdRessource());
            ressourceDTO.setLibelleRessource(entity.getRessource().getLibelleRessource());
        }
        dto.setRessource(ressourceDTO);
        return dto;
    }
}
