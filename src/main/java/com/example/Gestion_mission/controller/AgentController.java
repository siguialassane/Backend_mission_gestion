package com.example.Gestion_mission.controller;

import com.example.Gestion_mission.annotation.JournaliserAction;
import com.example.Gestion_mission.annotation.RoleAutorise;
import com.example.Gestion_mission.model.GmAgent;
import com.example.Gestion_mission.service.JournalUtilisateurService;
import com.example.Gestion_mission.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/profil")
    public ResponseEntity<?> getProfil() {
        Long idUtilisateur = roleService.getIdUtilisateurConnecte();
        String roleUtilisateur = roleService.getRoleUtilisateur();
        
        return ResponseEntity.ok("Profil de l'utilisateur ID: " + idUtilisateur + ", Role: " + roleUtilisateur);
    }

    @PostMapping("/creer")
    @RoleAutorise(roles = {"GESTIONNAIRE", "ADMIN"}, peutSupprimer = false, peutModifier = false)
    @JournaliserAction(entite = "GM_AGENT", action = "CREATE")
    public ResponseEntity<?> creerAgent(@RequestBody GmAgent agent) {
        // Ici, la logique de création d'agent
        return ResponseEntity.ok("Agent créé avec succès");
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