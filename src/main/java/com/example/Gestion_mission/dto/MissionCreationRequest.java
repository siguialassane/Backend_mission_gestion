package com.example.Gestion_mission.dto;

import java.util.Date;
import java.util.List;

public class MissionCreationRequest {

    private String objetMission;
    private Date dateDebutMission;
    private Date dateFinMission;
    private String lieuDepart;
    private String lieuDestination;
    private String motifMission;
    private String entiteCode;
    private Double fraisEstime;
    private String lieuDepartCode;
    private String lieuDestinationCode;
    private List<MissionParticipantCreationDTO> participants;
    private List<MissionRessourceCreationDTO> ressources;
    private List<MissionEtapeCreationDTO> etapes;

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

    public String getEntiteCode() {
        return entiteCode;
    }

    public void setEntiteCode(String entiteCode) {
        this.entiteCode = entiteCode;
    }

    public Double getFraisEstime() {
        return fraisEstime;
    }

    public void setFraisEstime(Double fraisEstime) {
        this.fraisEstime = fraisEstime;
    }

    public String getLieuDepartCode() {
        return lieuDepartCode;
    }

    public void setLieuDepartCode(String lieuDepartCode) {
        this.lieuDepartCode = lieuDepartCode;
    }

    public String getLieuDestinationCode() {
        return lieuDestinationCode;
    }

    public void setLieuDestinationCode(String lieuDestinationCode) {
        this.lieuDestinationCode = lieuDestinationCode;
    }

    public List<MissionParticipantCreationDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<MissionParticipantCreationDTO> participants) {
        this.participants = participants;
    }

    public List<MissionRessourceCreationDTO> getRessources() {
        return ressources;
    }

    public void setRessources(List<MissionRessourceCreationDTO> ressources) {
        this.ressources = ressources;
    }

    public List<MissionEtapeCreationDTO> getEtapes() {
        return etapes;
    }

    public void setEtapes(List<MissionEtapeCreationDTO> etapes) {
        this.etapes = etapes;
    }

    public static class MissionParticipantCreationDTO {
        private Long agentId;
        private String roleMission;

        public Long getAgentId() {
            return agentId;
        }

        public void setAgentId(Long agentId) {
            this.agentId = agentId;
        }

        public String getRoleMission() {
            return roleMission;
        }

        public void setRoleMission(String roleMission) {
            this.roleMission = roleMission;
        }
    }

    public static class MissionRessourceCreationDTO {
        private Long ressourceId;
        private Integer quantite;
        private String unite;
        private String commentaire;

        public Long getRessourceId() {
            return ressourceId;
        }

        public void setRessourceId(Long ressourceId) {
            this.ressourceId = ressourceId;
        }

        public Integer getQuantite() {
            return quantite;
        }

        public void setQuantite(Integer quantite) {
            this.quantite = quantite;
        }

        public String getUnite() {
            return unite;
        }

        public void setUnite(String unite) {
            this.unite = unite;
        }

        public String getCommentaire() {
            return commentaire;
        }

        public void setCommentaire(String commentaire) {
            this.commentaire = commentaire;
        }
    }

    public static class MissionEtapeCreationDTO {
        private Integer ordrePassage;
        private String villeDepartCode;
        private String villeDepartLibelle;
        private String villeArriveCode;
        private String villeArriveLibelle;
        private Date dateArrivee;
        private Date dateDepart;
        private String objectif;

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

        public String getVilleDepartLibelle() {
            return villeDepartLibelle;
        }

        public void setVilleDepartLibelle(String villeDepartLibelle) {
            this.villeDepartLibelle = villeDepartLibelle;
        }

        public String getVilleArriveCode() {
            return villeArriveCode;
        }

        public void setVilleArriveCode(String villeArriveCode) {
            this.villeArriveCode = villeArriveCode;
        }

        public String getVilleArriveLibelle() {
            return villeArriveLibelle;
        }

        public void setVilleArriveLibelle(String villeArriveLibelle) {
            this.villeArriveLibelle = villeArriveLibelle;
        }

        public Date getDateArrivee() {
            return dateArrivee;
        }

        public void setDateArrivee(Date dateArrivee) {
            this.dateArrivee = dateArrivee;
        }

        public Date getDateDepart() {
            return dateDepart;
        }

        public void setDateDepart(Date dateDepart) {
            this.dateDepart = dateDepart;
        }

        public String getObjectif() {
            return objectif;
        }

        public void setObjectif(String objectif) {
            this.objectif = objectif;
        }
    }
}
