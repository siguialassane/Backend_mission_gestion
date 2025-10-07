package com.example.Gestion_mission.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "GM_FRAIS_MISSION")
public class GmFraisMission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gm_frais_mission")
    @SequenceGenerator(name = "seq_gm_frais_mission", sequenceName = "SEQ_GM_FRAIS_MISSION", allocationSize = 1)
    @Column(name = "ID_FRAIS_MISSION")
    private Long idFraisMission;

    @Column(name = "ID_ORDRE_MISSION")
    private Long idOrdreMission;

    @Column(name = "TYPE_FRAIS")
    private String typeFrais;

    @Column(name = "MONTANT_FRAIS")
    private Double montantFrais;

    @Column(name = "DATE_FRAIS")
    private Date dateFrais;

    @Column(name = "DESCRIPTION_FRAIS")
    private String descriptionFrais;

    @Column(name = "JUSTIFICATIF_FRAIS")
    private String justificatifFrais;

    @Column(name = "DATE_CREATION")
    private Date dateCreation;

    @Column(name = "DATE_MISE_AJOUR")
    private Date dateMiseAJour;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "DELETED")
    private Integer deleted;

    @Column(name = "DELETED_AT")
    private Date deletedAt;

    // Getters and Setters

    public Long getIdFraisMission() {
        return idFraisMission;
    }

    public void setIdFraisMission(Long idFraisMission) {
        this.idFraisMission = idFraisMission;
    }

    public Long getIdOrdreMission() {
        return idOrdreMission;
    }

    public void setIdOrdreMission(Long idOrdreMission) {
        this.idOrdreMission = idOrdreMission;
    }

    public String getTypeFrais() {
        return typeFrais;
    }

    public void setTypeFrais(String typeFrais) {
        this.typeFrais = typeFrais;
    }

    public Double getMontantFrais() {
        return montantFrais;
    }

    public void setMontantFrais(Double montantFrais) {
        this.montantFrais = montantFrais;
    }

    public Date getDateFrais() {
        return dateFrais;
    }

    public void setDateFrais(Date dateFrais) {
        this.dateFrais = dateFrais;
    }

    public String getDescriptionFrais() {
        return descriptionFrais;
    }

    public void setDescriptionFrais(String descriptionFrais) {
        this.descriptionFrais = descriptionFrais;
    }

    public String getJustificatifFrais() {
        return justificatifFrais;
    }

    public void setJustificatifFrais(String justificatifFrais) {
        this.justificatifFrais = justificatifFrais;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateMiseAJour() {
        return dateMiseAJour;
    }

    public void setDateMiseAJour(Date dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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