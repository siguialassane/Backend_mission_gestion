package com.example.Gestion_mission.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "GM_MISSION_NOTIFICATION")
public class GmMissionNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gm_mission_notification")
    @SequenceGenerator(name = "seq_gm_mission_notification", sequenceName = "SEQ_GM_MISSION_NOTIFICATION", allocationSize = 1)
    @Column(name = "ID_NOTIFICATION")
    private Long idNotification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDRE_MISSION", nullable = false)
    @JsonIgnore
    private GmOrdreMission ordreMission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEST_AGENT")
    @JsonIgnore
    private GmAgent destinataireAgent;

    @Column(name = "DEST_ROLE")
    private String destinataireRole;

    @Column(name = "TYPE_NOTIFICATION")
    private String typeNotification;

    @Column(name = "MESSAGE_NOTIFICATION")
    private String messageNotification;

    @Column(name = "STATUT_NOTIFICATION")
    private String statutNotification;

    @Column(name = "CREATED_AT")
    private Date createdAt;

    @Column(name = "LECTURE_AT")
    private Date lectureAt;

    @Column(name = "CREATED_BY")
    private String createdBy;

    public Long getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Long idNotification) {
        this.idNotification = idNotification;
    }

    public GmOrdreMission getOrdreMission() {
        return ordreMission;
    }

    public void setOrdreMission(GmOrdreMission ordreMission) {
        this.ordreMission = ordreMission;
    }

    public GmAgent getDestinataireAgent() {
        return destinataireAgent;
    }

    public void setDestinataireAgent(GmAgent destinataireAgent) {
        this.destinataireAgent = destinataireAgent;
    }

    public String getDestinataireRole() {
        return destinataireRole;
    }

    public void setDestinataireRole(String destinataireRole) {
        this.destinataireRole = destinataireRole;
    }

    public String getTypeNotification() {
        return typeNotification;
    }

    public void setTypeNotification(String typeNotification) {
        this.typeNotification = typeNotification;
    }

    public String getMessageNotification() {
        return messageNotification;
    }

    public void setMessageNotification(String messageNotification) {
        this.messageNotification = messageNotification;
    }

    public String getStatutNotification() {
        return statutNotification;
    }

    public void setStatutNotification(String statutNotification) {
        this.statutNotification = statutNotification;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLectureAt() {
        return lectureAt;
    }

    public void setLectureAt(Date lectureAt) {
        this.lectureAt = lectureAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
