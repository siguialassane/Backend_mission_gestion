package com.example.Gestion_mission.model;

import jakarta.persistence.*;

@Entity
@Table(name = "GM_ROLE")
public class GmRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GM_ROLE_SEQ")
    @SequenceGenerator(name = "GM_ROLE_SEQ", sequenceName = "GM_ROLE_SEQ", allocationSize = 1)
    @Column(name = "ID_ROLE")
    private Long idRole;

    @Column(name = "NOM_ROLE")
    private String nomRole;

    // Getters and Setters

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }
}
