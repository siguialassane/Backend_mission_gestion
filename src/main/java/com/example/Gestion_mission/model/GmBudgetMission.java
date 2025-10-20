package com.example.Gestion_mission.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "GM_BUDGET_MISSION")
public class GmBudgetMission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gm_budget_mission")
    @SequenceGenerator(name = "seq_gm_budget_mission", sequenceName = "SEQ_GM_BUDGET_MISSION", allocationSize = 1)
    @Column(name = "ID_BUDGET")
    private Long idBudget;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDRE_MISSION", nullable = false)
    @JsonIgnore
    private GmOrdreMission ordreMission;

    @Column(name = "MONTANT_HEBERGEMENT")
    private BigDecimal montantHebergement;

    @Column(name = "MONTANT_RESTAURATION")
    private BigDecimal montantRestauration;

    @Column(name = "MONTANT_TRANSPORT")
    private BigDecimal montantTransport;

    @Column(name = "MONTANT_CARBURANT")
    private BigDecimal montantCarburant;

    @Column(name = "MONTANT_MISC")
    private BigDecimal montantDivers;

    @Column(name = "MONTANT_TOTAL")
    private BigDecimal montantTotal;

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

    public Long getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(Long idBudget) {
        this.idBudget = idBudget;
    }

    public GmOrdreMission getOrdreMission() {
        return ordreMission;
    }

    public void setOrdreMission(GmOrdreMission ordreMission) {
        this.ordreMission = ordreMission;
    }

    public BigDecimal getMontantHebergement() {
        return montantHebergement;
    }

    public void setMontantHebergement(BigDecimal montantHebergement) {
        this.montantHebergement = montantHebergement;
    }

    public BigDecimal getMontantRestauration() {
        return montantRestauration;
    }

    public void setMontantRestauration(BigDecimal montantRestauration) {
        this.montantRestauration = montantRestauration;
    }

    public BigDecimal getMontantTransport() {
        return montantTransport;
    }

    public void setMontantTransport(BigDecimal montantTransport) {
        this.montantTransport = montantTransport;
    }

    public BigDecimal getMontantCarburant() {
        return montantCarburant;
    }

    public void setMontantCarburant(BigDecimal montantCarburant) {
        this.montantCarburant = montantCarburant;
    }

    public BigDecimal getMontantDivers() {
        return montantDivers;
    }

    public void setMontantDivers(BigDecimal montantDivers) {
        this.montantDivers = montantDivers;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
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
}
