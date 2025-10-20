package com.example.Gestion_mission.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "AC_ENTITE")
public class AcEntite {

    @Id
    @Column(name = "ENTITE_CODE")
    private String code;

    @Column(name = "ENTITE_LIB")
    private String libelle;

    @Column(name = "ENTITE_LIB_LONG")
    private String libelleLong;

    @Column(name = "ENTITE_RESP")
    private String responsable;

    @Column(name = "ENTITE_ASSIGN")
    private String assignation;

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

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getAssignation() {
        return assignation;
    }

    public void setAssignation(String assignation) {
        this.assignation = assignation;
    }
}
