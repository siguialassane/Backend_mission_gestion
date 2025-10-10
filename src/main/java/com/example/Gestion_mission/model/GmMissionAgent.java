package com.example.Gestion_mission.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "GM_MISSION_AGENT")
public class GmMissionAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gm_mission_agent")
    @SequenceGenerator(name = "seq_gm_mission_agent", sequenceName = "SEQ_GM_MISSION_AGENT", allocationSize = 1)
    @Column(name = "ID_MISSION_AGENT")
    private Long idMissionAgent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDRE_MISSION", nullable = false)
    @JsonIgnore
    private GmOrdreMission ordreMission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AGENT", nullable = false)
    private GmAgent agent;

    @Column(name = "ROLE_MISSION")
    private String roleMission;

    @Column(name = "CREATED_AT")
    private Date createdAt;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    public Long getIdMissionAgent() {
        return idMissionAgent;
    }

    public void setIdMissionAgent(Long idMissionAgent) {
        this.idMissionAgent = idMissionAgent;
    }

    public GmOrdreMission getOrdreMission() {
        return ordreMission;
    }

    public void setOrdreMission(GmOrdreMission ordreMission) {
        this.ordreMission = ordreMission;
    }

    public GmAgent getAgent() {
        return agent;
    }

    public void setAgent(GmAgent agent) {
        this.agent = agent;
    }

    public String getRoleMission() {
        return roleMission;
    }

    public void setRoleMission(String roleMission) {
        this.roleMission = roleMission;
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
}
