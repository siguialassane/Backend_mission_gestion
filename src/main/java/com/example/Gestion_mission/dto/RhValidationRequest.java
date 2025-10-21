package com.example.Gestion_mission.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class RhValidationRequest {

    @NotBlank
    private String decision;

    private String motifRefus;

    private String commentaires;

    private String nomChauffeur;

    private String infoVehicule;

    private String nomAdjudant;

    private List<MissionCreationRequest.MissionRessourceCreationDTO> ressources;

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getMotifRefus() {
        return motifRefus;
    }

    public void setMotifRefus(String motifRefus) {
        this.motifRefus = motifRefus;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public List<MissionCreationRequest.MissionRessourceCreationDTO> getRessources() {
        return ressources;
    }

    public void setRessources(List<MissionCreationRequest.MissionRessourceCreationDTO> ressources) {
        this.ressources = ressources;
    }

    public String getNomChauffeur() {
        return nomChauffeur;
    }

    public void setNomChauffeur(String nomChauffeur) {
        this.nomChauffeur = nomChauffeur;
    }

    public String getInfoVehicule() {
        return infoVehicule;
    }

    public void setInfoVehicule(String infoVehicule) {
        this.infoVehicule = infoVehicule;
    }

    public String getNomAdjudant() {
        return nomAdjudant;
    }

    public void setNomAdjudant(String nomAdjudant) {
        this.nomAdjudant = nomAdjudant;
    }
}
