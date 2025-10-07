package com.example.Gestion_mission.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "GM_JOURNAL_UTILISATEUR")
public class GmJournalUtilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gm_journal_utilisateur")
    @SequenceGenerator(name = "seq_gm_journal_utilisateur", sequenceName = "SEQ_GM_JOURNAL_UTILISATEUR", allocationSize = 1)
    @Column(name = "ID_JOURNAL")
    private Long idJournal;

    @Column(name = "ID_AGENT")
    private Long idAgent;

    @Column(name = "ENTITE_CIBLE")
    private String entiteCible;

    @Column(name = "ACTION_EFFECTUEE")
    private String actionEffectuee;

    @Column(name = "DATE_ACTION")
    private Date dateAction;

    @Lob
    @Column(name = "DONNEES_AVANT")
    private String donneesAvant;

    @Lob
    @Column(name = "DONNEES_APRES")
    private String donneesApres;

    @Column(name = "COMMENTAIRES")
    private String commentaires;

    @Column(name = "ADRESSE_IP")
    private String adresseIp;

    @Column(name = "USER_AGENT")
    private String userAgent;

    @Column(name = "CREATED_AT")
    private Date createdAt;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "DELETED")
    private Integer deleted;

    @Column(name = "DELETED_AT")
    private Date deletedAt;

    // Getters and Setters

    public Long getIdJournal() {
        return idJournal;
    }

    public void setIdJournal(Long idJournal) {
        this.idJournal = idJournal;
    }

    public Long getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(Long idAgent) {
        this.idAgent = idAgent;
    }

    public String getEntiteCible() {
        return entiteCible;
    }

    public void setEntiteCible(String entiteCible) {
        this.entiteCible = entiteCible;
    }

    public String getActionEffectuee() {
        return actionEffectuee;
    }

    public void setActionEffectuee(String actionEffectuee) {
        this.actionEffectuee = actionEffectuee;
    }

    public Date getDateAction() {
        return dateAction;
    }

    public void setDateAction(Date dateAction) {
        this.dateAction = dateAction;
    }

    public String getDonneesAvant() {
        return donneesAvant;
    }

    public void setDonneesAvant(String donneesAvant) {
        this.donneesAvant = donneesAvant;
    }

    public String getDonneesApres() {
        return donneesApres;
    }

    public void setDonneesApres(String donneesApres) {
        this.donneesApres = donneesApres;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public String getAdresseIp() {
        return adresseIp;
    }

    public void setAdresseIp(String adresseIp) {
        this.adresseIp = adresseIp;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}