package com.example.Gestion_mission.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Décision de validation d'une mission présentée au frontend.
 */
public class ValidationWorkflowDTO {

    private Long idValidation;
    private String statutValidation;
    private String decision;
    private Date dateValidation;
    private String commentaires;
    private String motifRefus;
    private BigDecimal montantValide;
    private Integer fondsDisponible;
    private String referencePiece;
    private ValidationEtapeDTO etape;

    public Long getIdValidation() {
        return idValidation;
    }

    public void setIdValidation(Long idValidation) {
        this.idValidation = idValidation;
    }

    public String getStatutValidation() {
        return statutValidation;
    }

    public void setStatutValidation(String statutValidation) {
        this.statutValidation = statutValidation;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public Date getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
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

    public BigDecimal getMontantValide() {
        return montantValide;
    }

    public void setMontantValide(BigDecimal montantValide) {
        this.montantValide = montantValide;
    }

    public Integer getFondsDisponible() {
        return fondsDisponible;
    }

    public void setFondsDisponible(Integer fondsDisponible) {
        this.fondsDisponible = fondsDisponible;
    }

    public String getReferencePiece() {
        return referencePiece;
    }

    public void setReferencePiece(String referencePiece) {
        this.referencePiece = referencePiece;
    }

    public ValidationEtapeDTO getEtape() {
        return etape;
    }

    public void setEtape(ValidationEtapeDTO etape) {
        this.etape = etape;
    }

    public static class ValidationEtapeDTO {
        private String codeEtape;
        private String libelleEtape;

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
    }
}
