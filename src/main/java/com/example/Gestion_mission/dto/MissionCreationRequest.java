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
    private List<MissionParticipantCreationDTO> participants;

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

    public List<MissionParticipantCreationDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<MissionParticipantCreationDTO> participants) {
        this.participants = participants;
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
}
