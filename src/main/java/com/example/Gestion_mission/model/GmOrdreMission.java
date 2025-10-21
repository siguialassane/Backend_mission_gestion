package com.example.Gestion_mission.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
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

    @Column(name = "ENTITE_CODE")
    private String entiteCode;

    @Column(name = "STATUT_MISSION")
    private String statutMission;

    @Column(name = "WORKFLOW_PHASE")
    private String workflowPhase;

    @Column(name = "WORKFLOW_STATUT")
    private String workflowStatut;

    @Column(name = "MOTIF_REFUS_GLOBAL")
    private String motifRefusGlobal;

    @Column(name = "PDF_CHEF_URI")
    private String pdfChefUri;

    @Column(name = "PDF_RH_URI")
    private String pdfRhUri;

    @Column(name = "PDF_FINAL_URI")
    private String pdfFinalUri;

    @Column(name = "DATE_VALIDATION_RH")
    private Date dateValidationRh;

    @Column(name = "DATE_VALIDATION_MG")
    private Date dateValidationMg;

    @Column(name = "DATE_VALIDATION_CAISSE")
    private Date dateValidationCaisse;

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

    @Column(name = "SIG_FONDE_RECUE")
    private Integer signatureFondeRecue;

    @Column(name = "SIG_AGENTCPT_RECUE")
    private Integer signatureAgentComptableRecue;

    @Column(name = "NOM_CHAUFFEUR")
    private String nomChauffeur;

    @Column(name = "INFO_VEHICULE")
    private String infoVehicule;

    @Column(name = "NOM_ADJUDANT")
    private String nomAdjudant;

    @Column(name = "DESTINATAIRE")
    private String destinataire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AGENT_VALID_RH")
    private GmAgent agentValidateurRh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AGENT_VALID_MG")
    private GmAgent agentValidateurMg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AGENT_VALID_CAISSE")
    private GmAgent agentValidateurCaisse;

    @OneToMany(mappedBy = "ordreMission", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<GmMissionRessource> ressources = new HashSet<>();

    @OneToMany(mappedBy = "ordreMission", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<GmMissionEtape> etapes = new HashSet<>();

    @OneToOne(mappedBy = "ordreMission", cascade = CascadeType.ALL, orphanRemoval = false)
    private GmRemiseJustificatifs remiseJustificatifs;

    @OneToMany(mappedBy = "ordreMission", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<GmMissionAgent> participants = new HashSet<>();

    @OneToOne(mappedBy = "ordreMission", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private GmBudgetMission budget;

    @OneToMany(mappedBy = "ordreMission", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<GmMissionNotification> notifications = new LinkedHashSet<>();

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

    public String getEntiteCode() {
        return entiteCode;
    }

    public void setEntiteCode(String entiteCode) {
        this.entiteCode = entiteCode;
    }

    public String getStatutMission() {
        return statutMission;
    }

    public void setStatutMission(String statutMission) {
        this.statutMission = statutMission;
    }

    public String getWorkflowPhase() {
        return workflowPhase;
    }

    public void setWorkflowPhase(String workflowPhase) {
        this.workflowPhase = workflowPhase;
    }

    public String getWorkflowStatut() {
        return workflowStatut;
    }

    public void setWorkflowStatut(String workflowStatut) {
        this.workflowStatut = workflowStatut;
    }

    public String getMotifRefusGlobal() {
        return motifRefusGlobal;
    }

    public void setMotifRefusGlobal(String motifRefusGlobal) {
        this.motifRefusGlobal = motifRefusGlobal;
    }

    public String getPdfChefUri() {
        return pdfChefUri;
    }

    public void setPdfChefUri(String pdfChefUri) {
        this.pdfChefUri = pdfChefUri;
    }

    public String getPdfRhUri() {
        return pdfRhUri;
    }

    public void setPdfRhUri(String pdfRhUri) {
        this.pdfRhUri = pdfRhUri;
    }

    public String getPdfFinalUri() {
        return pdfFinalUri;
    }

    public void setPdfFinalUri(String pdfFinalUri) {
        this.pdfFinalUri = pdfFinalUri;
    }

    public Date getDateValidationRh() {
        return dateValidationRh;
    }

    public void setDateValidationRh(Date dateValidationRh) {
        this.dateValidationRh = dateValidationRh;
    }

    public Date getDateValidationMg() {
        return dateValidationMg;
    }

    public void setDateValidationMg(Date dateValidationMg) {
        this.dateValidationMg = dateValidationMg;
    }

    public Date getDateValidationCaisse() {
        return dateValidationCaisse;
    }

    public void setDateValidationCaisse(Date dateValidationCaisse) {
        this.dateValidationCaisse = dateValidationCaisse;
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

    public Set<GmMissionAgent> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<GmMissionAgent> participants) {
        this.participants = participants;
    }

    public GmAgent getAgentValidateurRh() {
        return agentValidateurRh;
    }

    public void setAgentValidateurRh(GmAgent agentValidateurRh) {
        this.agentValidateurRh = agentValidateurRh;
    }

    public GmAgent getAgentValidateurMg() {
        return agentValidateurMg;
    }

    public void setAgentValidateurMg(GmAgent agentValidateurMg) {
        this.agentValidateurMg = agentValidateurMg;
    }

    public GmAgent getAgentValidateurCaisse() {
        return agentValidateurCaisse;
    }

    public void setAgentValidateurCaisse(GmAgent agentValidateurCaisse) {
        this.agentValidateurCaisse = agentValidateurCaisse;
    }

    public GmBudgetMission getBudget() {
        return budget;
    }

    public void setBudget(GmBudgetMission budget) {
        this.budget = budget;
        if (budget != null) {
            budget.setOrdreMission(this);
        }
    }

    public Set<GmMissionNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<GmMissionNotification> notifications) {
        this.notifications = notifications;
    }

    public Integer getSignatureFondeRecue() {
        return signatureFondeRecue;
    }

    public void setSignatureFondeRecue(Integer signatureFondeRecue) {
        this.signatureFondeRecue = signatureFondeRecue;
    }

    public Integer getSignatureAgentComptableRecue() {
        return signatureAgentComptableRecue;
    }

    public void setSignatureAgentComptableRecue(Integer signatureAgentComptableRecue) {
        this.signatureAgentComptableRecue = signatureAgentComptableRecue;
    }

    public String getNomChauffeur() {
        return nomChauffeur;
    }

    public void setNomChauffeur(String nomChauffeur) {
        this.nomChauffeur = nomChauffeur;
    }

    public String getInfoVehicule() {
        return infoVehicule;
    }

    public void setInfoVehicule(String infoVehicule) {
        this.infoVehicule = infoVehicule;
    }

    public String getNomAdjudant() {
        return nomAdjudant;
    }

    public void setNomAdjudant(String nomAdjudant) {
        this.nomAdjudant = nomAdjudant;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    // Méthode désactivée temporairement pour éviter les cycles de dépendance
    // public Set<GmAgent> getAgents() {
    //     return agents;
    // }

    // public void setAgents(Set<GmAgent> agents) {
    //     this.agents = agents;
    // }
}