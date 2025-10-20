package com.example.Gestion_mission.dto;

public class ClotureRhRequest {

    private String decision;

    private String commentaires;

    private String motifRefus;

    private Boolean ordreImprime;

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public String getMotifRefus() {
        return motifRefus;
    }

    public void setMotifRefus(String motifRefus) {
        this.motifRefus = motifRefus;
    }

    public Boolean getOrdreImprime() {
        return ordreImprime;
    }

    public void setOrdreImprime(Boolean ordreImprime) {
        this.ordreImprime = ordreImprime;
    }
}
