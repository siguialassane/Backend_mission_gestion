package com.example.Gestion_mission.model;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Ressource disponible pour les missions (véhicule, matériel, etc.).
 */
@Entity
@Table(name = "GM_RESSOURCE")
public class GmRessource {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gm_ressource")
    @SequenceGenerator(name = "seq_gm_ressource", sequenceName = "SEQ_GM_RESSOURCE", allocationSize = 1)
    @Column(name = "ID_RESSCR")
    private Long idRessource;

    @Lob
    @Column(name = "LIB_RESSCR")
    private String libelleRessource;

    @Column(name = "CREATED_AT")
    private Date createdAt;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "DELETED")
    private Integer deleted;

    @Column(name = "DELETED_AT")
    private Date deletedAt;

    public Long getIdRessource() {
        return idRessource;
    }

    public void setIdRessource(Long idRessource) {
        this.idRessource = idRessource;
    }

    public String getLibelleRessource() {
        return libelleRessource;
    }

    public void setLibelleRessource(String libelleRessource) {
        this.libelleRessource = libelleRessource;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}
