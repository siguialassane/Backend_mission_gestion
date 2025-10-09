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
    private Date dateCreation;
    private Date dateMiseAJour;

    // Constructeur vide
    public OrdreMissionDTO() {
    }

    // Constructeur complet
    public OrdreMissionDTO(Long idOrdreMission, String codeMission, String objetMission,
                          Date dateDebutMission, Date dateFinMission, String lieuDepart,
                          String lieuDestination, String motifMission, Double fraisEstime,
                          Long idAgentCreateur, String statutMission, Date dateCreation,
                          Date dateMiseAJour) {
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
        this.dateCreation = dateCreation;
        this.dateMiseAJour = dateMiseAJour;
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
}
