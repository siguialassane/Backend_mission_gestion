package com.example.Gestion_mission.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;

/**
 * Étape détaillée d'un déplacement au sein d'une mission.
 */
@Entity
@Table(name = "GM_MISSION_ETAPE")
public class GmMissionEtape {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gm_mission_etape")
    @SequenceGenerator(name = "seq_gm_mission_etape", sequenceName = "SEQ_GM_MISSION_ETAPE", allocationSize = 1)
    @Column(name = "ID_MISSION_ETAPE")
    private Long idMissionEtape;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDRE_MISSION", nullable = false)
    @JsonIgnore
    private GmOrdreMission ordreMission;

    @Column(name = "ORDRE_PASSAGE", nullable = false)
    private Integer ordrePassage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ITINERAIRE")
    private GmItineraire itineraire;

    @Column(name = "VILLE_DEPART_CODE")
    private String villeDepartCode;

    @Column(name = "VILLE_ARRIVE_CODE")
    private String villeArriveCode;

    @Column(name = "DATE_DEPART")
    private Date dateDepart;

    @Column(name = "DATE_ARRIVEE")
    private Date dateArrivee;

    @Column(name = "MODE_TRANSPORT")
    private String modeTransport;

    @Column(name = "HEBERGEMENT_PREVU")
    private String hebergementPrevu;

    @Column(name = "COMMENTAIRE_ETAPE")
    private String commentaireEtape;

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

    public Long getIdMissionEtape() {
        return idMissionEtape;
    }

    public void setIdMissionEtape(Long idMissionEtape) {
        this.idMissionEtape = idMissionEtape;
    }

    public GmOrdreMission getOrdreMission() {
        return ordreMission;
    }

    public void setOrdreMission(GmOrdreMission ordreMission) {
        this.ordreMission = ordreMission;
    }

    public Integer getOrdrePassage() {
        return ordrePassage;
    }

    public void setOrdrePassage(Integer ordrePassage) {
        this.ordrePassage = ordrePassage;
    }

    public GmItineraire getItineraire() {
        return itineraire;
    }

    public void setItineraire(GmItineraire itineraire) {
        this.itineraire = itineraire;
    }

    public String getVilleDepartCode() {
        return villeDepartCode;
    }

    public void setVilleDepartCode(String villeDepartCode) {
        this.villeDepartCode = villeDepartCode;
    }

    public String getVilleArriveCode() {
        return villeArriveCode;
    }

    public void setVilleArriveCode(String villeArriveCode) {
        this.villeArriveCode = villeArriveCode;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public String getModeTransport() {
        return modeTransport;
    }

    public void setModeTransport(String modeTransport) {
        this.modeTransport = modeTransport;
    }

    public String getHebergementPrevu() {
        return hebergementPrevu;
    }

    public void setHebergementPrevu(String hebergementPrevu) {
        this.hebergementPrevu = hebergementPrevu;
    }

    public String getCommentaireEtape() {
        return commentaireEtape;
    }

    public void setCommentaireEtape(String commentaireEtape) {
        this.commentaireEtape = commentaireEtape;
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
