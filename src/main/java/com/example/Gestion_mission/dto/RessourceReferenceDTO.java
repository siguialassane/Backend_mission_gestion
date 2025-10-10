package com.example.Gestion_mission.dto;

public class RessourceReferenceDTO {

    private Long id;
    private String libelle;

    public RessourceReferenceDTO() {
    }

    public RessourceReferenceDTO(Long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
