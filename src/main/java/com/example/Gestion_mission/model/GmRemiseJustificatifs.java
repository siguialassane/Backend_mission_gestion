package com.example.Gestion_mission.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;

/**
 * Confirme la remise des documents de fin de mission aux Moyens Généraux.
 */
@Entity
@Table(name = "GM_REMISE_JUSTIFICATIFS")
public class GmRemiseJustificatifs {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gm_remise_justificatifs")
    @SequenceGenerator(name = "seq_gm_remise_justificatifs", sequenceName = "SEQ_GM_REMISE_JUSTIFICATIFS", allocationSize = 1)
    @Column(name = "ID_REMISE")
    private Long idRemise;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDRE_MISSION", nullable = false, unique = true)
    @JsonIgnore
    private GmOrdreMission ordreMission;

    @Column(name = "RECU_RAPPORT")
    private Integer recuRapport;

    @Column(name = "RECU_FACTURE")
    private Integer recuFacture;

    @Column(name = "RECU_ORDRE")
    private Integer recuOrdre;

    @Column(name = "DATE_REMISE")
    private Date dateRemise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AGENT_RECEPTEUR")
    private GmAgent agentRecepteur;

    @Column(name = "OBSERVATIONS")
    private String observations;

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

    public Long getIdRemise() {
        return idRemise;
    }

    public void setIdRemise(Long idRemise) {
        this.idRemise = idRemise;
    }

    public GmOrdreMission getOrdreMission() {
        return ordreMission;
    }

    public void setOrdreMission(GmOrdreMission ordreMission) {
        this.ordreMission = ordreMission;
    }

    public Integer getRecuRapport() {
        return recuRapport;
    }

    public void setRecuRapport(Integer recuRapport) {
        this.recuRapport = recuRapport;
    }

    public Integer getRecuFacture() {
        return recuFacture;
    }

    public void setRecuFacture(Integer recuFacture) {
        this.recuFacture = recuFacture;
    }

    public Integer getRecuOrdre() {
        return recuOrdre;
    }

    public void setRecuOrdre(Integer recuOrdre) {
        this.recuOrdre = recuOrdre;
    }

    public Date getDateRemise() {
        return dateRemise;
    }

    public void setDateRemise(Date dateRemise) {
        this.dateRemise = dateRemise;
    }

    public GmAgent getAgentRecepteur() {
        return agentRecepteur;
    }

    public void setAgentRecepteur(GmAgent agentRecepteur) {
        this.agentRecepteur = agentRecepteur;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
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
