package com.example.Gestion_mission.controller;

import com.example.Gestion_mission.annotation.JournaliserAction;
import com.example.Gestion_mission.annotation.RoleAutorise;
import com.example.Gestion_mission.dto.AgentCreationRequest;
import com.example.Gestion_mission.model.GmAgent;
import com.example.Gestion_mission.service.AgentService;
import com.example.Gestion_mission.service.JournalUtilisateurService;
import com.example.Gestion_mission.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agents")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AgentController {

    @Autowired
    private JournalUtilisateurService journalService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private AgentService agentService;

    @GetMapping("/profil")
    public ResponseEntity<?> getProfil() {
        Long idUtilisateur = roleService.getIdUtilisateurConnecte();
        String roleUtilisateur = roleService.getRoleUtilisateur();
        
        return ResponseEntity.ok("Profil de l'utilisateur ID: " + idUtilisateur + ", Role: " + roleUtilisateur);
    }

    @PostMapping
    @JournaliserAction(entite = "GM_AGENT", action = "CREATE")
    public ResponseEntity<?> creerAgent(@RequestBody AgentCreationRequest request) {
        try {
            System.out.println("📝 [AGENT-CREATE] Création d'agent: " + request.getNom() + " " + request.getPrenom());
            GmAgent agentCree = agentService.creerAgent(request);
            System.out.println("✅ [AGENT-CREATE] Agent créé avec ID: " + agentCree.getIdAgent());
            return ResponseEntity.status(HttpStatus.CREATED).body(agentCree);
        } catch (IllegalArgumentException e) {
            System.err.println("❌ [AGENT-CREATE] Erreur validation: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ [AGENT-CREATE] Erreur inattendue: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création de l'agent");
        }
    }

    @PutMapping("/modifier/{id}")
    @RoleAutorise(roles = {"ADMIN"}, peutSupprimer = false, peutModifier = true)
    @JournaliserAction(entite = "GM_AGENT", action = "UPDATE")
    public ResponseEntity<?> modifierAgent(@PathVariable Long id, @RequestBody GmAgent agent) {
        // Les gestionnaires ne peuvent pas appeler cette méthode à cause de peutModifier = true
        return ResponseEntity.ok("Agent modifié avec succès");
    }

    @DeleteMapping("/supprimer/{id}")
    @RoleAutorise(roles = {"ADMIN"}, peutSupprimer = true, peutModifier = false)
    public ResponseEntity<?> supprimerAgent(@PathVariable Long id) {
        // Les gestionnaires ne peuvent pas appeler cette méthode à cause de peutSupprimer = true
        return ResponseEntity.ok("Agent supprimé avec succès");
    }
}