package com.example.Gestion_mission.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "GM_ORDRE_MISSION")
public class GmOrdreMission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gm_ordre_mission")
    @SequenceGenerator(name = "seq_gm_ordre_mission", sequenceName = "SEQ_GM_ORDRE_MISSION", allocationSize = 1)
    @Column(name = "ID_ORDRE_MISSION")
    private Long idOrdreMission;

    @Column(name = "CODE_MISSION")
    private String codeMission;

    @Column(name = "OBJET_MISSION")
    private String objetMission;

    @Column(name = "DATE_DEBUT_MISSION")
    private Date dateDebutMission;

    @Column(name = "DATE_FIN_MISSION")
    private Date dateFinMission;

    @Column(name = "LIEU_DEPART")
    private String lieuDepart;

    @Column(name = "LIEU_DESTINATION")
    private String lieuDestination;

    @Column(name = "MOTIF_MISSION")
    private String motifMission;

    @Column(name = "FRAIS_ESTIME")
    private Double fraisEstime;

    @Column(name = "ID_AGENT_CREATEUR")
    private Long idAgentCreateur;

    @Column(name = "STATUT_MISSION")
    private String statutMission;

    @Column(name = "DATE_CREATION")
    private Date dateCreation;

    @Column(name = "DATE_MISE_AJOUR")
    private Date dateMiseAJour;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "DELETED")
    private Integer deleted;

    @Column(name = "DELETED_AT")
    private Date deletedAt;

    @OneToMany(mappedBy = "ordreMission", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<GmMissionRessource> ressources = new HashSet<>();

    @OneToMany(mappedBy = "ordreMission", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<GmMissionEtape> etapes = new HashSet<>();

    @OneToOne(mappedBy = "ordreMission", cascade = CascadeType.ALL, orphanRemoval = false)
    private GmRemiseJustificatifs remiseJustificatifs;

    // Getters and Setters

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

    public Double getFraisEstime() {
        return fraisEstime;
    }

    public void setFraisEstime(Double fraisEstime) {
        this.fraisEstime = fraisEstime;
    }

    public Long getIdAgentCreateur() {
        return idAgentCreateur;
    }

    public void setIdAgentCreateur(Long idAgentCreateur) {
        this.idAgentCreateur = idAgentCreateur;
    }

    public String getStatutMission() {
        return statutMission;
    }

    public void setStatutMission(String statutMission) {
        this.statutMission = statutMission;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateMiseAJour() {
        return dateMiseAJour;
    }

    public void setDateMiseAJour(Date dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

    public Set<GmMissionRessource> getRessources() {
        return ressources;
    }

    public void setRessources(Set<GmMissionRessource> ressources) {
        this.ressources = ressources;
    }

    public Set<GmMissionEtape> getEtapes() {
        return etapes;
    }

    public void setEtapes(Set<GmMissionEtape> etapes) {
        this.etapes = etapes;
    }

    public GmRemiseJustificatifs getRemiseJustificatifs() {
        return remiseJustificatifs;
    }

    public void setRemiseJustificatifs(GmRemiseJustificatifs remiseJustificatifs) {
        this.remiseJustificatifs = remiseJustificatifs;
    }

    // Méthode désactivée temporairement pour éviter les cycles de dépendance
    // public Set<GmAgent> getAgents() {
    //     return agents;
    // }

    // public void setAgents(Set<GmAgent> agents) {
    //     this.agents = agents;
    // }
}