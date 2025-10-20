package com.example.Gestion_mission.dto;

import java.util.Date;

/**
 * DTO pour transférer les données d'ordre de mission vers le frontend
 * Évite les problèmes de sérialisation circulaire JPA
 */
public class OrdreMissionDTO {

    private Long idOrdreMission;
    private String codeMission;
    private String objetMission;
    private Date dateDebutMission;
    private Date dateFinMission;
    private String lieuDepart;
    private String lieuDestination;
    private String motifMission;
    private Double fraisEstime;
    private Long idAgentCreateur;
    private String statutMission;
    private String workflowPhase;
    private String workflowStatut;
    private String motifRefusGlobal;
    private String pdfChefUri;
    private String pdfRhUri;
    private String pdfFinalUri;
    private Date dateCreation;
    private Date dateMiseAJour;
    private String entiteCode;
    private Boolean signatureFondeRecue;
    private Boolean signatureAgentComptableRecue;
    private Date dateValidationRh;
    private Date dateValidationMg;
    private Date dateValidationCaisse;

    // Constructeur vide
    public OrdreMissionDTO() {
    }

    // Constructeur complet
    public OrdreMissionDTO(Long idOrdreMission, String codeMission, String objetMission,
                          Date dateDebutMission, Date dateFinMission, String lieuDepart,
                          String lieuDestination, String motifMission, Double fraisEstime,
                          Long idAgentCreateur, String statutMission, String workflowPhase,
                          String workflowStatut, String motifRefusGlobal, String pdfChefUri,
                          String pdfRhUri, String pdfFinalUri, Date dateCreation,
                          Date dateMiseAJour, String entiteCode,
                          Boolean signatureFondeRecue, Boolean signatureAgentComptableRecue,
                          Date dateValidationRh, Date dateValidationMg, Date dateValidationCaisse) {
        this.idOrdreMission = idOrdreMission;
        this.codeMission = codeMission;
        this.objetMission = objetMission;
        this.dateDebutMission = dateDebutMission;
        this.dateFinMission = dateFinMission;
        this.lieuDepart = lieuDepart;
        this.lieuDestination = lieuDestination;
        this.motifMission = motifMission;
        this.fraisEstime = fraisEstime;
        this.idAgentCreateur = idAgentCreateur;
        this.statutMission = statutMission;
        this.workflowPhase = workflowPhase;
        this.workflowStatut = workflowStatut;
        this.motifRefusGlobal = motifRefusGlobal;
        this.pdfChefUri = pdfChefUri;
        this.pdfRhUri = pdfRhUri;
        this.pdfFinalUri = pdfFinalUri;
        this.dateCreation = dateCreation;
        this.dateMiseAJour = dateMiseAJour;
        this.entiteCode = entiteCode;
        this.signatureFondeRecue = signatureFondeRecue;
        this.signatureAgentComptableRecue = signatureAgentComptableRecue;
        this.dateValidationRh = dateValidationRh;
        this.dateValidationMg = dateValidationMg;
        this.dateValidationCaisse = dateValidationCaisse;
    }

    // Getters et Setters
    public Long getIdOrdreMission() {
        return idOrdreMission;
    }

    public void setIdOrdreMission(Long idOrdreMission) {
        this.idOrdreMission = idOrdreMission;
    }

    public String getCodeMission() {
        return codeMission;
    }

    public void setCodeMission(String codeMission) {
        this.codeMission = codeMission;
    }

    public String getObjetMission() {
        return objetMission;
    }

    public void setObjetMission(String objetMission) {
        this.objetMission = objetMission;
    }

    public Date getDateDebutMission() {
        return dateDebutMission;
    }

    public void setDateDebutMission(Date dateDebutMission) {
        this.dateDebutMission = dateDebutMission;
    }

    public Date getDateFinMission() {
        return dateFinMission;
    }

    public void setDateFinMission(Date dateFinMission) {
        this.dateFinMission = dateFinMission;
    }

    public String getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public String getLieuDestination() {
        return lieuDestination;
    }

    public void setLieuDestination(String lieuDestination) {
        this.lieuDestination = lieuDestination;
    }

    public String getMotifMission() {
        return motifMission;
    }

    public void setMotifMission(String motifMission) {
        this.motifMission = motifMission;
    }

    public Double getFraisEstime() {
        return fraisEstime;
    }

    public void setFraisEstime(Double fraisEstime) {
        this.fraisEstime = fraisEstime;
    }

    public Long getIdAgentCreateur() {
        return idAgentCreateur;
    }

    public void setIdAgentCreateur(Long idAgentCreateur) {
        this.idAgentCreateur = idAgentCreateur;
    }

    public String getStatutMission() {
        return statutMission;
    }

    public void setStatutMission(String statutMission) {
        this.statutMission = statutMission;
    }

    public String getWorkflowPhase() {
        return workflowPhase;
    }

    public void setWorkflowPhase(String workflowPhase) {
        this.workflowPhase = workflowPhase;
    }

    public String getWorkflowStatut() {
        return workflowStatut;
    }

    public void setWorkflowStatut(String workflowStatut) {
        this.workflowStatut = workflowStatut;
    }

    public String getMotifRefusGlobal() {
        return motifRefusGlobal;
    }

    public void setMotifRefusGlobal(String motifRefusGlobal) {
        this.motifRefusGlobal = motifRefusGlobal;
    }

    public String getPdfChefUri() {
        return pdfChefUri;
    }

    public void setPdfChefUri(String pdfChefUri) {
        this.pdfChefUri = pdfChefUri;
    }

    public String getPdfRhUri() {
        return pdfRhUri;
    }

    public void setPdfRhUri(String pdfRhUri) {
        this.pdfRhUri = pdfRhUri;
    }

    public String getPdfFinalUri() {
        return pdfFinalUri;
    }

    public void setPdfFinalUri(String pdfFinalUri) {
        this.pdfFinalUri = pdfFinalUri;
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

    public String getEntiteCode() {
        return entiteCode;
    }

    public void setEntiteCode(String entiteCode) {
        this.entiteCode = entiteCode;
    }

    public Boolean getSignatureFondeRecue() {
        return signatureFondeRecue;
    }

    public void setSignatureFondeRecue(Boolean signatureFondeRecue) {
        this.signatureFondeRecue = signatureFondeRecue;
    }

    public Boolean getSignatureAgentComptableRecue() {
        return signatureAgentComptableRecue;
    }

    public void setSignatureAgentComptableRecue(Boolean signatureAgentComptableRecue) {
        this.signatureAgentComptableRecue = signatureAgentComptableRecue;
    }

    public Date getDateValidationRh() {
        return dateValidationRh;
    }

    public void setDateValidationRh(Date dateValidationRh) {
        this.dateValidationRh = dateValidationRh;
    }

    public Date getDateValidationMg() {
        return dateValidationMg;
    }

    public void setDateValidationMg(Date dateValidationMg) {
        this.dateValidationMg = dateValidationMg;
    }

    public Date getDateValidationCaisse() {
        return dateValidationCaisse;
    }

    public void setDateValidationCaisse(Date dateValidationCaisse) {
        this.dateValidationCaisse = dateValidationCaisse;
    }
}
