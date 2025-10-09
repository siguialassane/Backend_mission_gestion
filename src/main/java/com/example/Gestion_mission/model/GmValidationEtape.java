package com.example.Gestion_mission.model;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Référentiel des étapes de validation d'une mission.
 */
@Entity
@Table(name = "GM_VALIDATION_ETAPE")
public class GmValidationEtape {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gm_validation_etape")
    @SequenceGenerator(name = "seq_gm_validation_etape", sequenceName = "SEQ_GM_VALIDATION_ETAPE", allocationSize = 1)
    @Column(name = "ID_ETAPE")
    private Long idEtape;

    @Column(name = "CODE_ETAPE", nullable = false, unique = true)
    private String codeEtape;

    @Column(name = "LIBELLE_ETAPE", nullable = false)
    private String libelleEtape;

    @Column(name = "ORDRE_ETAPE")
    private Integer ordreEtape;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_REQUIS")
    private GmRole roleRequis;

    @Column(name = "DESCRIPTION_ETAPE")
    private String descriptionEtape;

    @Column(name = "ACTIF")
    private Integer actif;

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

    public Long getIdEtape() {
        return idEtape;
    }

    public void setIdEtape(Long idEtape) {
        this.idEtape = idEtape;
    }

    public String getCodeEtape() {
        return codeEtape;
    }

    public void setCodeEtape(String codeEtape) {
        this.codeEtape = codeEtape;
    }

    public String getLibelleEtape() {
        return libelleEtape;
    }

    public void setLibelleEtape(String libelleEtape) {
        this.libelleEtape = libelleEtape;
    }

    public Integer getOrdreEtape() {
        return ordreEtape;
    }

    public void setOrdreEtape(Integer ordreEtape) {
        this.ordreEtape = ordreEtape;
    }

    public GmRole getRoleRequis() {
        return roleRequis;
    }

    public void setRoleRequis(GmRole roleRequis) {
        this.roleRequis = roleRequis;
    }

    public String getDescriptionEtape() {
        return descriptionEtape;
    }

    public void setDescriptionEtape(String descriptionEtape) {
        this.descriptionEtape = descriptionEtape;
    }

    public Integer getActif() {
        return actif;
    }

    public void setActif(Integer actif) {
        this.actif = actif;
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
