package com.example.Gestion_mission.controller;

import com.example.Gestion_mission.annotation.JournaliserAction;
import com.example.Gestion_mission.annotation.RoleAutorise;
import com.example.Gestion_mission.dto.AgentReferenceDTO;
import com.example.Gestion_mission.dto.RessourceReferenceDTO;
import com.example.Gestion_mission.dto.VilleDTO;
import com.example.Gestion_mission.model.GmRessource;
import com.example.Gestion_mission.service.AgentService;
import com.example.Gestion_mission.service.RessourceService;
import com.example.Gestion_mission.service.VilleService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/referentiel")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReferentielController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ReferentielController.class);

    private final VilleService villeService;
    private final RessourceService ressourceService;
    private final AgentService agentService;

    public ReferentielController(VilleService villeService,
                                 RessourceService ressourceService,
                                 AgentService agentService) {
        this.villeService = villeService;
        this.ressourceService = ressourceService;
        this.agentService = agentService;
    }

    @GetMapping("/villes")
    public ResponseEntity<Page<VilleDTO>> listerVilles(@RequestParam(value = "q", required = false) String query,
                                                       @RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "size", defaultValue = "20") int size) {
        log.info("🔹 CONTROLLER /villes REACHED - query: '{}', page: {}, size: {}", query, page, size);
        Page<VilleDTO> result = villeService.rechercher(query, page, size)
                .map(ville -> new VilleDTO(ville.getCode(), ville.getLibelle(), ville.getLibelleLong()))
                ;
        log.info("🔹 CONTROLLER /villes RESPONSE - {} villes found", result.getTotalElements());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/ressources")
    public ResponseEntity<List<RessourceReferenceDTO>> listerRessources() {
        List<RessourceReferenceDTO> ressources = ressourceService.listerDisponibles().stream()
                .map(this::mapRessource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ressources);
    }

    @GetMapping("/agents")
    @RoleAutorise(roles = {"ADMIN", "GESTIONNAIRE"}, peutModifier = false, peutSupprimer = false)
    @JournaliserAction(entite = "GM_AGENT", action = "READ")
    public ResponseEntity<Page<AgentReferenceDTO>> rechercherAgents(@RequestParam("q") String query,
                                                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                                                    @RequestParam(value = "size", defaultValue = "10") int size) {
        if (query == null || query.isBlank()) {
            return ResponseEntity.ok(Page.empty());
        }
        Page<AgentReferenceDTO> result = agentService.rechercher(query, page, size)
                .map(agent -> new AgentReferenceDTO(
                        agent.getIdAgent(),
                        agent.getMatriculeAgent(),
                        agent.getNomAgent(),
                        agent.getPrenomAgent()
                ));
        return ResponseEntity.ok(result);
    }

    private RessourceReferenceDTO mapRessource(GmRessource ressource) {
        return new RessourceReferenceDTO(ressource.getIdRessource(), ressource.getLibelleRessource());
    }
}
