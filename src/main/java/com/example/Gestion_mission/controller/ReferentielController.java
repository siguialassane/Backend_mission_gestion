package com.example.Gestion_mission.controller;

import com.example.Gestion_mission.annotation.JournaliserAction;
import com.example.Gestion_mission.annotation.RoleAutorise;
import com.example.Gestion_mission.dto.AgentCreationRequest;
import com.example.Gestion_mission.dto.AgentDetailDTO;
import com.example.Gestion_mission.dto.AgentReferenceDTO;
import com.example.Gestion_mission.dto.EntiteDTO;
import com.example.Gestion_mission.dto.RessourceReferenceDTO;
import com.example.Gestion_mission.dto.VilleDTO;
import com.example.Gestion_mission.service.AgentService;
import com.example.Gestion_mission.service.EntiteService;
import com.example.Gestion_mission.service.RessourceService;
import com.example.Gestion_mission.service.RoleService;
import com.example.Gestion_mission.service.VilleService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/referentiel")
@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
public class ReferentielController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ReferentielController.class);

    private final VilleService villeService;
    private final RessourceService ressourceService;
    private final EntiteService entiteService;
    private final AgentService agentService;
    private final RoleService roleService;

    public ReferentielController(VilleService villeService,
                                 RessourceService ressourceService,
                                 EntiteService entiteService,
                                 AgentService agentService,
                                 RoleService roleService) {
        this.villeService = villeService;
        this.ressourceService = ressourceService;
        this.entiteService = entiteService;
        this.agentService = agentService;
        this.roleService = roleService;
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
                .map(ressource -> new RessourceReferenceDTO(ressource.getIdRessource(), ressource.getLibelleRessource()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ressources);
    }

    @GetMapping("/entites")
    public ResponseEntity<Page<EntiteDTO>> listerEntites(@RequestParam(value = "q", required = false) String query,
                                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                                         @RequestParam(value = "size", defaultValue = "20") int size) {
        Page<EntiteDTO> result = entiteService.rechercher(query, page, size)
                .map(entite -> new EntiteDTO(entite.getCode(), entite.getLibelle(), entite.getLibelleLong()));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/agents")
    @RoleAutorise(roles = {"ADMIN", "GESTIONNAIRE", "RH", "CHEF_SERVICE"}, peutModifier = false, peutSupprimer = false)
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

    @PostMapping("/agents")
    @RoleAutorise(roles = {"ADMIN", "GESTIONNAIRE", "RH", "CHEF_SERVICE"}, peutModifier = true, peutSupprimer = false)
    @JournaliserAction(entite = "GM_AGENT", action = "CREATE")
    public ResponseEntity<AgentReferenceDTO> creerAgent(@RequestBody @Valid AgentCreationRequest request) {
        var agentCree = agentService.creerAgent(request);
        AgentReferenceDTO dto = new AgentReferenceDTO(
                agentCree.getIdAgent(),
                agentCree.getMatriculeAgent(),
                agentCree.getNomAgent(),
                agentCree.getPrenomAgent()
        );
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/agents/mine")
    @RoleAutorise(roles = {"CHEF_SERVICE", "ADMIN"}, peutModifier = false, peutSupprimer = false)
    @JournaliserAction(entite = "GM_AGENT", action = "READ")
    public ResponseEntity<List<AgentReferenceDTO>> listerAgentsDuCreateur() {
        Long utilisateurId = roleService.getIdUtilisateurConnecte();
        if (utilisateurId == null) {
            return ResponseEntity.ok(List.of());
        }

        List<AgentReferenceDTO> agents = agentService.listerAgentsCreesParUtilisateur(utilisateurId).stream()
                .map(agent -> new AgentReferenceDTO(
                        agent.getIdAgent(),
                        agent.getMatriculeAgent(),
                        agent.getNomAgent(),
                        agent.getPrenomAgent()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(agents);
    }

    @GetMapping("/agents/{id}")
    @RoleAutorise(roles = {"CHEF_SERVICE", "ADMIN", "GESTIONNAIRE", "RH"}, peutModifier = false, peutSupprimer = false)
    @JournaliserAction(entite = "GM_AGENT", action = "READ")
    public ResponseEntity<AgentDetailDTO> lireAgent(@PathVariable Long id) {
        var agent = agentService.lireAgent(id);
        return ResponseEntity.ok(new AgentDetailDTO(
                agent.getIdAgent(),
                agent.getMatriculeAgent(),
                agent.getNomAgent(),
                agent.getPrenomAgent(),
                agent.getEmailAgent(),
                agent.getTelephoneAgent(),
                agent.getFonctionAgent(),
                agent.getGradeAgent(),
                agent.getStatutActifAgent()
        ));
    }

    @PutMapping("/agents/{id}")
    @RoleAutorise(roles = {"CHEF_SERVICE", "ADMIN"}, peutModifier = true, peutSupprimer = false)
    @JournaliserAction(entite = "GM_AGENT", action = "UPDATE")
    public ResponseEntity<AgentReferenceDTO> modifierAgent(@PathVariable Long id, 
                                                           @RequestBody @Valid AgentCreationRequest request) {
        var agentModifie = agentService.modifierAgent(id, request);
        return ResponseEntity.ok(new AgentReferenceDTO(
                agentModifie.getIdAgent(),
                agentModifie.getMatriculeAgent(),
                agentModifie.getNomAgent(),
                agentModifie.getPrenomAgent()
        ));
    }

    @DeleteMapping("/agents/{id}")
    @RoleAutorise(roles = {"CHEF_SERVICE", "ADMIN"}, peutModifier = true, peutSupprimer = true)
    @JournaliserAction(entite = "GM_AGENT", action = "DELETE")
    public ResponseEntity<?> supprimerAgent(@PathVariable Long id) {
        agentService.supprimerAgent(id);
        return ResponseEntity.ok(java.util.Map.of("message", "Agent supprimé avec succès"));
    }
}
