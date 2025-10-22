package com.example.Gestion_mission.dto;

import java.util.Date;

public class MissionDetailViewDTO {
    private Long idOrdreMission;
    private String codeMission;
    private String objetMission;
    private Date dateDebutMission;
    private Date dateFinMission;
    private String lieuDepart;
    private String lieuDestination;
    private String motifMission;
    private String statutMission;
    private String entiteCode;
    private String entiteLibelle;
    private String nomChauffeur;
    private String infoVehicule;
    private String nomAdjudant;
    private String destinataire;
    private Date dateMiseAJour;
    private Date dateCreation;
    private String chefMissionNom;
    private String chefMissionPrenom;
    private Long nombreAgents;
    private String itineraireVilles;
    private String participantsListe;

    // Constructeur
    public MissionDetailViewDTO() {}

    // Getters & Setters
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

    public String getStatutMission() {
        return statutMission;
    }

    public void setStatutMission(String statutMission) {
        this.statutMission = statutMission;
    }

    public String getEntiteCode() {
        return entiteCode;
    }

    public void setEntiteCode(String entiteCode) {
        this.entiteCode = entiteCode;
    }

    public String getEntiteLibelle() {
        return entiteLibelle;
    }

    public void setEntiteLibelle(String entiteLibelle) {
        this.entiteLibelle = entiteLibelle;
    }

    public String getNomChauffeur() {
        return nomChauffeur;
    }

    public void setNomChauffeur(String nomChauffeur) {
        this.nomChauffeur = nomChauffeur;
    }

    public String getInfoVehicule() {
        return infoVehicule;
    }

    public void setInfoVehicule(String infoVehicule) {
        this.infoVehicule = infoVehicule;
    }

    public String getNomAdjudant() {
        return nomAdjudant;
    }

    public void setNomAdjudant(String nomAdjudant) {
        this.nomAdjudant = nomAdjudant;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public Date getDateMiseAJour() {
        return dateMiseAJour;
    }

    public void setDateMiseAJour(Date dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getChefMissionNom() {
        return chefMissionNom;
    }

    public void setChefMissionNom(String chefMissionNom) {
        this.chefMissionNom = chefMissionNom;
    }

    public String getChefMissionPrenom() {
        return chefMissionPrenom;
    }

    public void setChefMissionPrenom(String chefMissionPrenom) {
        this.chefMissionPrenom = chefMissionPrenom;
    }

    public Long getNombreAgents() {
        return nombreAgents;
    }

    public void setNombreAgents(Long nombreAgents) {
        this.nombreAgents = nombreAgents;
    }

    public String getItineraireVilles() {
        return itineraireVilles;
    }

    public void setItineraireVilles(String itineraireVilles) {
        this.itineraireVilles = itineraireVilles;
    }

    public String getParticipantsListe() {
        return participantsListe;
    }

    public void setParticipantsListe(String participantsListe) {
        this.participantsListe = participantsListe;
    }
}
