package com.example.Gestion_mission.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Journalise chaque étape de validation d'une mission.
 */
@Entity
@Table(name = "GM_VALIDATION_WORKFLOW")
public class GmValidationWorkflow {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gm_validation_workflow")
    @SequenceGenerator(name = "seq_gm_validation_workflow", sequenceName = "SEQ_GM_VALIDATION_WORKFLOW", allocationSize = 1)
    @Column(name = "ID_VALIDATION")
    private Long idValidation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDRE_MISSION")
    private GmOrdreMission ordreMission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ETAPE")
    private GmValidationEtape etape;

    @Column(name = "NIVEAU_VALIDATION")
    private Integer niveauValidation;

    @Column(name = "ORDRE_VALIDATION")
    private Integer ordreValidation;

    @Column(name = "STATUT_VALIDATION")
    private String statutValidation;

    @Column(name = "DATE_VALIDATION")
    private Date dateValidation;

    @Lob
    @Column(name = "COMMENTAIRES")
    private String commentaires;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AGENT_VALIDEUR")
    private GmAgent agentValideur;

    @Column(name = "DECISION")
    private String decision;

    @Column(name = "MOTIF_REFUS")
    private String motifRefus;

    @Column(name = "MONTANT_VALIDE")
    private BigDecimal montantValide;

    @Column(name = "FONDS_DISPONIBLE")
    private Integer fondsDisponible;

    @Column(name = "REFERENCE_PIECE")
    private String referencePiece;

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

    public Long getIdValidation() {
        return idValidation;
    }

    public void setIdValidation(Long idValidation) {
        this.idValidation = idValidation;
    }

    public GmOrdreMission getOrdreMission() {
        return ordreMission;
    }

    public void setOrdreMission(GmOrdreMission ordreMission) {
        this.ordreMission = ordreMission;
    }

    public GmValidationEtape getEtape() {
        return etape;
    }

    public void setEtape(GmValidationEtape etape) {
        this.etape = etape;
    }

    public Integer getNiveauValidation() {
        return niveauValidation;
    }

    public void setNiveauValidation(Integer niveauValidation) {
        this.niveauValidation = niveauValidation;
    }

    public Integer getOrdreValidation() {
        return ordreValidation;
    }

    public void setOrdreValidation(Integer ordreValidation) {
        this.ordreValidation = ordreValidation;
    }

    public String getStatutValidation() {
        return statutValidation;
    }

    public void setStatutValidation(String statutValidation) {
        this.statutValidation = statutValidation;
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

    public GmAgent getAgentValideur() {
        return agentValideur;
    }

    public void setAgentValideur(GmAgent agentValideur) {
        this.agentValideur = agentValideur;
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
