package com.example.Gestion_mission.controller;

import com.example.Gestion_mission.model.GmTemplateMotif;
import com.example.Gestion_mission.service.TemplateMotifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/templates")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TemplateMotifController {

    @Autowired
    private TemplateMotifService templateService;

    /**
     * Récupérer tous les templates de l'utilisateur connecté
     */
    @GetMapping
    public ResponseEntity<List<GmTemplateMotif>> getMesTemplates() {
        try {
            System.out.println("📋 [TEMPLATE] Récupération des templates");
            List<GmTemplateMotif> templates = templateService.getMesTemplates();
            System.out.println("✅ [TEMPLATE] " + templates.size() + " template(s) trouvé(s)");
            return ResponseEntity.ok(templates);
        } catch (Exception e) {
            System.err.println("❌ [TEMPLATE] Erreur: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Récupérer un template spécifique
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getTemplate(@PathVariable Long id) {
        try {
            System.out.println("📋 [TEMPLATE] Récupération template ID: " + id);
            GmTemplateMotif template = templateService.getTemplate(id);
            return ResponseEntity.ok(template);
        } catch (IllegalArgumentException e) {
            System.err.println("❌ [TEMPLATE] " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ [TEMPLATE] Erreur: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur serveur");
        }
    }

    /**
     * Créer un nouveau template
     */
    @PostMapping
    public ResponseEntity<?> creerTemplate(@RequestBody Map<String, String> payload) {
        try {
            String nomTemplate = payload.get("nomTemplate");
            String contenuTemplate = payload.get("contenuTemplate");
            
            System.out.println("📝 [TEMPLATE] Création template: " + nomTemplate);
            
            GmTemplateMotif template = templateService.creerTemplate(nomTemplate, contenuTemplate);
            
            System.out.println("✅ [TEMPLATE] Template créé avec ID: " + template.getIdTemplate());
            return ResponseEntity.status(HttpStatus.CREATED).body(template);
        } catch (IllegalArgumentException e) {
            System.err.println("❌ [TEMPLATE] Validation: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ [TEMPLATE] Erreur: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création du template");
        }
    }

    /**
     * Modifier un template existant
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> modifierTemplate(
            @PathVariable Long id,
            @RequestBody Map<String, String> payload) {
        try {
            String nomTemplate = payload.get("nomTemplate");
            String contenuTemplate = payload.get("contenuTemplate");
            
            System.out.println("✏️ [TEMPLATE] Modification template ID: " + id);
            
            GmTemplateMotif template = templateService.modifierTemplate(id, nomTemplate, contenuTemplate);
            
            System.out.println("✅ [TEMPLATE] Template modifié");
            return ResponseEntity.ok(template);
        } catch (IllegalArgumentException e) {
            System.err.println("❌ [TEMPLATE] " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ [TEMPLATE] Erreur: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la modification du template");
        }
    }

    /**
     * Supprimer un template
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerTemplate(@PathVariable Long id) {
        try {
            System.out.println("🗑️ [TEMPLATE] Suppression template ID: " + id);
            templateService.supprimerTemplate(id);
            System.out.println("✅ [TEMPLATE] Template supprimé");
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            System.err.println("❌ [TEMPLATE] " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ [TEMPLATE] Erreur: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression du template");
        }
    }
}
