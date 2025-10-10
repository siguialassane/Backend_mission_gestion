package com.example.Gestion_mission.dto;

public class MissionParticipantDTO {

    private Long idMissionAgent;
    private Long agentId;
    private String matricule;
    private String nom;
    private String prenom;
    private String roleMission;

    public MissionParticipantDTO() {
    }

    public MissionParticipantDTO(Long idMissionAgent, Long agentId, String matricule, String nom, String prenom, String roleMission) {
        this.idMissionAgent = idMissionAgent;
        this.agentId = agentId;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.roleMission = roleMission;
    }

    public Long getIdMissionAgent() {
        return idMissionAgent;
    }

    public void setIdMissionAgent(Long idMissionAgent) {
        this.idMissionAgent = idMissionAgent;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRoleMission() {
        return roleMission;
    }

    public void setRoleMission(String roleMission) {
        this.roleMission = roleMission;
    }
}
