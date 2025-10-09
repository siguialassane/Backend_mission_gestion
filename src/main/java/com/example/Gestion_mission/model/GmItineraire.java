package com.example.Gestion_mission.model;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Modèle d'itinéraire pouvant être réutilisé sur plusieurs missions.
 */
@Entity
@Table(name = "GM_ITINERAIRE")
public class GmItineraire {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gm_itineraire")
    @SequenceGenerator(name = "seq_gm_itineraire", sequenceName = "SEQ_GM_ITINERAIRE", allocationSize = 1)
    @Column(name = "ID_ITINERAIRE")
    private Long idItineraire;

    @Lob
    @Column(name = "LIB_ITINERAIRE")
    private String libelleItineraire;

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

    public Long getIdItineraire() {
        return idItineraire;
    }

    public void setIdItineraire(Long idItineraire) {
        this.idItineraire = idItineraire;
    }

    public String getLibelleItineraire() {
        return libelleItineraire;
    }

    public void setLibelleItineraire(String libelleItineraire) {
        this.libelleItineraire = libelleItineraire;
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
