package com.example.Gestion_mission.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ValidationMgRequest {

    @NotNull
    private BigDecimal montantValide;

    private BigDecimal montantHebergement;

    private BigDecimal montantRestauration;

    private BigDecimal montantTransport;

    private BigDecimal montantCarburant;

    private BigDecimal montantDivers;

    private String commentaires;

    private Boolean signatureFondeRecue;

    private String decision;

    private String motifRefus;

    public BigDecimal getMontantValide() {
        return montantValide;
    }

    public void setMontantValide(BigDecimal montantValide) {
        this.montantValide = montantValide;
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

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public Boolean getSignatureFondeRecue() {
        return signatureFondeRecue;
    }

    public void setSignatureFondeRecue(Boolean signatureFondeRecue) {
        this.signatureFondeRecue = signatureFondeRecue;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getMotifRefus() {
        return motifRefus;
    }

    public void setMotifRefus(String motifRefus) {
        this.motifRefus = motifRefus;
    }
}
