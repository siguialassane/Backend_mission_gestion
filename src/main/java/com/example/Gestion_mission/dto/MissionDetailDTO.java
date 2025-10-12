package com.example.Gestion_mission.dto;

import java.util.List;

/**
 * Vue agrégée d'une mission et de toutes ses composantes.
 */
public class MissionDetailDTO {

    private OrdreMissionDTO mission;
    private List<MissionRessourceDTO> ressources;
    private List<MissionEtapeDTO> etapes;
    private List<ValidationWorkflowDTO> workflow;
    private RemiseJustificatifsDTO justificatifs;
    private BudgetMissionDTO budget;
    private List<MissionNotificationDTO> notifications;
    private List<MissionParticipantDTO> participants;

    public OrdreMissionDTO getMission() {
        return mission;
    }

    public void setMission(OrdreMissionDTO mission) {
        this.mission = mission;
    }

    public List<MissionRessourceDTO> getRessources() {
        return ressources;
    }

    public void setRessources(List<MissionRessourceDTO> ressources) {
        this.ressources = ressources;
    }

    public List<MissionEtapeDTO> getEtapes() {
        return etapes;
    }

    public void setEtapes(List<MissionEtapeDTO> etapes) {
        this.etapes = etapes;
    }

    public List<ValidationWorkflowDTO> getWorkflow() {
        return workflow;
    }

    public void setWorkflow(List<ValidationWorkflowDTO> workflow) {
        this.workflow = workflow;
    }

    public RemiseJustificatifsDTO getJustificatifs() {
        return justificatifs;
    }

    public void setJustificatifs(RemiseJustificatifsDTO justificatifs) {
        this.justificatifs = justificatifs;
    }

    public BudgetMissionDTO getBudget() {
        return budget;
    }

    public void setBudget(BudgetMissionDTO budget) {
        this.budget = budget;
    }

    public List<MissionNotificationDTO> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<MissionNotificationDTO> notifications) {
        this.notifications = notifications;
    }

    public List<MissionParticipantDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<MissionParticipantDTO> participants) {
        this.participants = participants;
    }
}
