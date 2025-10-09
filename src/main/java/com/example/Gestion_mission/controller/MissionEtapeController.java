package com.example.Gestion_mission.controller;

import com.example.Gestion_mission.annotation.JournaliserAction;
import com.example.Gestion_mission.annotation.RoleAutorise;
import com.example.Gestion_mission.model.GmItineraire;
import com.example.Gestion_mission.model.GmMissionEtape;
import com.example.Gestion_mission.service.MissionEtapeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/missions/{missionId}/etapes")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MissionEtapeController {

    private static final Logger log = LoggerFactory.getLogger(MissionEtapeController.class);

    private final MissionEtapeService missionEtapeService;

    public MissionEtapeController(MissionEtapeService missionEtapeService) {
        this.missionEtapeService = missionEtapeService;
    }

    @GetMapping
    @JournaliserAction(entite = "GM_MISSION_ETAPE", action = "READ")
    public ResponseEntity<List<GmMissionEtape>> lister(@PathVariable Long missionId) {
        log.debug("API - Consultation des étapes pour la mission {}", missionId);
        return ResponseEntity.ok(missionEtapeService.listerEtapes(missionId));
    }

    @PostMapping
    @RoleAutorise(roles = {"GESTIONNAIRE", "ADMIN"}, peutModifier = true)
    @JournaliserAction(entite = "GM_MISSION_ETAPE", action = "CREATE")
    public ResponseEntity<GmMissionEtape> ajouter(@PathVariable Long missionId,
                                                 @RequestBody MissionEtapeRequest request) {
        log.info("API - Ajout de l'étape {} pour la mission {}", request.ordrePassage(), missionId);
        GmMissionEtape etape = new GmMissionEtape();
        etape.setOrdrePassage(request.ordrePassage());
        etape.setVilleDepartCode(request.villeDepartCode());
        etape.setVilleArriveCode(request.villeArriveCode());
        etape.setDateDepart(request.dateDepart());
        etape.setDateArrivee(request.dateArrivee());
        etape.setModeTransport(request.modeTransport());
        etape.setHebergementPrevu(request.hebergementPrevu());
        etape.setCommentaireEtape(request.commentaireEtape());

        if (request.idItineraire() != null) {
            GmItineraire itineraire = new GmItineraire();
            itineraire.setIdItineraire(request.idItineraire());
            etape.setItineraire(itineraire);
        }

        return ResponseEntity.ok(missionEtapeService.ajouterEtape(missionId, etape));
    }

    @DeleteMapping("/{etapeId}")
    @RoleAutorise(roles = {"ADMIN"}, peutSupprimer = true)
    @JournaliserAction(entite = "GM_MISSION_ETAPE", action = "DELETE")
    public ResponseEntity<Void> supprimer(@PathVariable Long missionId, @PathVariable Long etapeId) {
        log.warn("API - Suppression de l'étape {} pour la mission {}", etapeId, missionId);
        missionEtapeService.supprimerEtape(etapeId);
        return ResponseEntity.ok().build();
    }

    public record MissionEtapeRequest(Integer ordrePassage,
                                      Long idItineraire,
                                      String villeDepartCode,
                                      String villeArriveCode,
                                      Date dateDepart,
                                      Date dateArrivee,
                                      String modeTransport,
                                      String hebergementPrevu,
                                      String commentaireEtape) {
    }
}
