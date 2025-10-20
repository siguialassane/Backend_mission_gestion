package com.example.Gestion_mission.dto;

import java.util.Date;

public class MissionNotificationDTO {

    private Long idNotification;
    private Long ordreMissionId;
    private Long destinataireAgentId;
    private String destinataireRole;
    private String typeNotification;
    private String messageNotification;
    private String statutNotification;
    private Date createdAt;
    private Date lectureAt;

    public Long getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Long idNotification) {
        this.idNotification = idNotification;
    }

    public Long getOrdreMissionId() {
        return ordreMissionId;
    }

    public void setOrdreMissionId(Long ordreMissionId) {
        this.ordreMissionId = ordreMissionId;
    }

    public Long getDestinataireAgentId() {
        return destinataireAgentId;
    }

    public void setDestinataireAgentId(Long destinataireAgentId) {
        this.destinataireAgentId = destinataireAgentId;
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
}
