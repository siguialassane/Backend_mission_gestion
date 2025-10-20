package com.example.Gestion_mission.dto;

import jakarta.validation.constraints.NotNull;

public class ValidationCaisseRequest {

    @NotNull
    private Boolean fondsDisponible;

    private String referencePiece;

    private String decision;

    private String motifRefus;

    private Boolean signatureAgentComptableRecue;

    public Boolean getFondsDisponible() {
        return fondsDisponible;
    }

    public void setFondsDisponible(Boolean fondsDisponible) {
        this.fondsDisponible = fondsDisponible;
    }

    public String getReferencePiece() {
        return referencePiece;
    }

    public void setReferencePiece(String referencePiece) {
        this.referencePiece = referencePiece;
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

    public Boolean getSignatureAgentComptableRecue() {
        return signatureAgentComptableRecue;
    }

    public void setSignatureAgentComptableRecue(Boolean signatureAgentComptableRecue) {
        this.signatureAgentComptableRecue = signatureAgentComptableRecue;
    }
}
