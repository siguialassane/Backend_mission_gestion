package com.example.Gestion_mission.dto;

public class AgentReferenceDTO {

    private Long id;
    private String matricule;
    private String nom;
    private String prenom;

    public AgentReferenceDTO() {
    }

    public AgentReferenceDTO(Long id, String matricule, String nom, String prenom) {
        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
