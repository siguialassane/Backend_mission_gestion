package com.example.Gestion_mission.dto;

public class VilleDTO {

    private String code;
    private String libelle;
    private String libelleLong;

    public VilleDTO() {
    }

    public VilleDTO(String code, String libelle, String libelleLong) {
        this.code = code;
        this.libelle = libelle;
        this.libelleLong = libelleLong;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelleLong() {
        return libelleLong;
    }

    public void setLibelleLong(String libelleLong) {
        this.libelleLong = libelleLong;
    }
}
