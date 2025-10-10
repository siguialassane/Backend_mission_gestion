package com.example.Gestion_mission.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "AC_VILLE")
public class AcVille {

    @Id
    @Column(name = "VILLE_CODE")
    private String code;

    @Column(name = "VILLE_LIB")
    private String libelle;

    @Column(name = "VILLE_LIB_LONG")
    private String libelleLong;

    @Column(name = "VILLE_NUM")
    private String numero;

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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
