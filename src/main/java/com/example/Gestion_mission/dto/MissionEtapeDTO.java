package com.example.Gestion_mission.dto;

import java.util.Date;

/**
 * Étape d'itinéraire exposée à l'interface utilisateur.
 */
public class MissionEtapeDTO {

    private Long idMissionEtape;
    private Integer ordrePassage;
    private String villeDepartCode;
    private String villeArriveCode;
    private Date dateDepart;
    private Date dateArrivee;
    private String modeTransport;
    private String hebergementPrevu;
    private String commentaireEtape;

    public Long getIdMissionEtape() {
        return idMissionEtape;
    }

    public void setIdMissionEtape(Long idMissionEtape) {
        this.idMissionEtape = idMissionEtape;
    }

    public Integer getOrdrePassage() {
        return ordrePassage;
    }

    public void setOrdrePassage(Integer ordrePassage) {
        this.ordrePassage = ordrePassage;
    }

    public String getVilleDepartCode() {
        return villeDepartCode;
    }

    public void setVilleDepartCode(String villeDepartCode) {
        this.villeDepartCode = villeDepartCode;
    }

    public String getVilleArriveCode() {
        return villeArriveCode;
    }

    public void setVilleArriveCode(String villeArriveCode) {
        this.villeArriveCode = villeArriveCode;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public String getModeTransport() {
        return modeTransport;
    }

    public void setModeTransport(String modeTransport) {
        this.modeTransport = modeTransport;
    }

    public String getHebergementPrevu() {
        return hebergementPrevu;
    }

    public void setHebergementPrevu(String hebergementPrevu) {
        this.hebergementPrevu = hebergementPrevu;
    }

    public String getCommentaireEtape() {
        return commentaireEtape;
    }

    public void setCommentaireEtape(String commentaireEtape) {
        this.commentaireEtape = commentaireEtape;
    }
}
