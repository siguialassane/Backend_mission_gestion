package com.example.Gestion_mission.service;

import com.example.Gestion_mission.model.GmTemplateMotif;
import com.example.Gestion_mission.repository.GmTemplateMotifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TemplateMotifService {

    private final GmTemplateMotifRepository templateRepository;
    private final RoleService roleService;

    @Autowired
    public TemplateMotifService(GmTemplateMotifRepository templateRepository, RoleService roleService) {
        this.templateRepository = templateRepository;
        this.roleService = roleService;
        System.out.println("✅ [TEMPLATE-SERVICE] Service initialisé");
    }

    /**
     * Récupérer tous les templates de l'utilisateur connecté
     */
    public List<GmTemplateMotif> getMesTemplates() {
        Long utilisateurId = roleService.getIdUtilisateurConnecte();
        if (utilisateurId == null) {
            throw new IllegalStateException("Utilisateur non connecté");
        }
        return templateRepository.findByIdUtilisateurCreateurOrderByDateCreationDesc(utilisateurId);
    }

    /**
     * Créer un nouveau template
     */
    public GmTemplateMotif creerTemplate(String nomTemplate, String contenuTemplate) {
        Long utilisateurId = roleService.getIdUtilisateurConnecte();
        if (utilisateurId == null) {
            throw new IllegalStateException("Utilisateur non connecté");
        }

        if (nomTemplate == null || nomTemplate.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du template est obligatoire");
        }

        if (contenuTemplate == null || contenuTemplate.trim().isEmpty()) {
            throw new IllegalArgumentException("Le contenu du template est obligatoire");
        }

        GmTemplateMotif template = new GmTemplateMotif();
        template.setIdUtilisateurCreateur(utilisateurId);
        template.setNomTemplate(nomTemplate.trim());
        template.setContenuTemplate(contenuTemplate.trim());
        template.setDateCreation(new Date());

        return templateRepository.save(template);
    }

    /**
     * Modifier un template existant
     */
    public GmTemplateMotif modifierTemplate(Long idTemplate, String nomTemplate, String contenuTemplate) {
        Long utilisateurId = roleService.getIdUtilisateurConnecte();
        if (utilisateurId == null) {
            throw new IllegalStateException("Utilisateur non connecté");
        }

        GmTemplateMotif template = templateRepository.findById(idTemplate)
                .orElseThrow(() -> new IllegalArgumentException("Template non trouvé: " + idTemplate));

        // Vérifier que l'utilisateur est le créateur
        if (!template.getIdUtilisateurCreateur().equals(utilisateurId)) {
            throw new IllegalArgumentException("Vous ne pouvez modifier que vos propres templates");
        }

        if (nomTemplate != null && !nomTemplate.trim().isEmpty()) {
            template.setNomTemplate(nomTemplate.trim());
        }

        if (contenuTemplate != null && !contenuTemplate.trim().isEmpty()) {
            template.setContenuTemplate(contenuTemplate.trim());
        }

        template.setDateModification(new Date());

        return templateRepository.save(template);
    }

    /**
     * Supprimer un template
     */
    public void supprimerTemplate(Long idTemplate) {
        Long utilisateurId = roleService.getIdUtilisateurConnecte();
        if (utilisateurId == null) {
            throw new IllegalStateException("Utilisateur non connecté");
        }

        GmTemplateMotif template = templateRepository.findById(idTemplate)
                .orElseThrow(() -> new IllegalArgumentException("Template non trouvé: " + idTemplate));

        // Vérifier que l'utilisateur est le créateur
        if (!template.getIdUtilisateurCreateur().equals(utilisateurId)) {
            throw new IllegalArgumentException("Vous ne pouvez supprimer que vos propres templates");
        }

        templateRepository.delete(template);
    }

    /**
     * Récupérer un template spécifique
     */
    public GmTemplateMotif getTemplate(Long idTemplate) {
        Long utilisateurId = roleService.getIdUtilisateurConnecte();
        if (utilisateurId == null) {
            throw new IllegalStateException("Utilisateur non connecté");
        }

        GmTemplateMotif template = templateRepository.findById(idTemplate)
                .orElseThrow(() -> new IllegalArgumentException("Template non trouvé: " + idTemplate));

        // Vérifier que l'utilisateur est le créateur
        if (!template.getIdUtilisateurCreateur().equals(utilisateurId)) {
            throw new IllegalArgumentException("Vous ne pouvez consulter que vos propres templates");
        }

        return template;
    }
}
