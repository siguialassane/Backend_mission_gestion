package com.example.Gestion_mission.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;

/**
 * Ressources matérielles allouées à une mission.
 */
@Entity
@Table(name = "GM_MISSION_RESSOURCE")
public class GmMissionRessource {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gm_mission_ressource")
    @SequenceGenerator(name = "seq_gm_mission_ressource", sequenceName = "SEQ_GM_MISSION_RESSOURCE", allocationSize = 1)
    @Column(name = "ID_MISSION_RESSOURCE")
    private Long idMissionRessource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDRE_MISSION", nullable = false)
    @JsonIgnore
    private GmOrdreMission ordreMission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RESSCR", nullable = false)
    private GmRessource ressource;

    @Column(name = "QUANTITE")
    private Integer quantite;

    @Column(name = "UNITE")
    private String unite;

    @Column(name = "COMMENTAIRE")
    private String commentaire;

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

    public Long getIdMissionRessource() {
        return idMissionRessource;
    }

    public void setIdMissionRessource(Long idMissionRessource) {
        this.idMissionRessource = idMissionRessource;
    }

    public GmOrdreMission getOrdreMission() {
        return ordreMission;
    }

    public void setOrdreMission(GmOrdreMission ordreMission) {
        this.ordreMission = ordreMission;
    }

    public GmRessource getRessource() {
        return ressource;
    }

    public void setRessource(GmRessource ressource) {
        this.ressource = ressource;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
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
