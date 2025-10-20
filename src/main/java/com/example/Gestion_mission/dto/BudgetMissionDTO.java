package com.example.Gestion_mission.dto;

import java.math.BigDecimal;
import java.util.Date;

public class BudgetMissionDTO {

    private Long idBudget;
    private BigDecimal montantHebergement;
    private BigDecimal montantRestauration;
    private BigDecimal montantTransport;
    private BigDecimal montantCarburant;
    private BigDecimal montantDivers;
    private BigDecimal montantTotal;
    private String commentaire;
    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;

    public Long getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(Long idBudget) {
        this.idBudget = idBudget;
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
