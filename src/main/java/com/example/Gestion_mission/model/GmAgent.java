package com.example.Gestion_mission.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "GM_AGENT")
public class GmAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gm_agent_seq")
    @SequenceGenerator(name = "gm_agent_seq", sequenceName = "SEQ_GM_AGENT", allocationSize = 1)
    @Column(name = "ID_AGENT")
    private Long idAgent;

    @Column(name = "MATRICULE_AGENT")
    private String matriculeAgent;

    @Column(name = "NOM_AGENT")
    private String nomAgent;

    @Column(name = "PRENOM_AGENT")
    private String prenomAgent;

    @Column(name = "EMAIL_AGENT")
    private String emailAgent;

    @Column(name = "TELEPHONE_AGENT")
    private String telephoneAgent;

    @Column(name = "FONCTION_AGENT")
    private String fonctionAgent;

    @Column(name = "GRADE_AGENT")
    private String gradeAgent;

    @Column(name = "ID_STRUCTURE_AGENT")
    private Long idStructureAgent;

    @Column(name = "STATUT_ACTIF_AGENT")
    private Integer statutActifAgent;

    @Column(name = "DATE_CREATION_AGENT")
    private Date dateCreationAgent;

    @Column(name = "ENTITE_CODE")
    private String entiteCode;

    @Column(name = "MOT_DE_PASSE")
    private String motDePasse;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "GM_AGENT_ROLE",
               joinColumns = @JoinColumn(name = "ID_AGENT"),
               inverseJoinColumns = @JoinColumn(name = "ID_ROLE"))
    private Set<GmRole> roles = new HashSet<>();

    // Getters and Setters

    public Long getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(Long idAgent) {
        this.idAgent = idAgent;
    }

    public String getMatriculeAgent() {
        return matriculeAgent;
    }

    public void setMatriculeAgent(String matriculeAgent) {
        this.matriculeAgent = matriculeAgent;
    }

    public String getNomAgent() {
        return nomAgent;
    }

    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent;
    }

    public String getPrenomAgent() {
        return prenomAgent;
    }

    public void setPrenomAgent(String prenomAgent) {
        this.prenomAgent = prenomAgent;
    }

    public String getEmailAgent() {
        return emailAgent;
    }

    public void setEmailAgent(String emailAgent) {
        this.emailAgent = emailAgent;
    }

    public String getTelephoneAgent() {
        return telephoneAgent;
    }

    public void setTelephoneAgent(String telephoneAgent) {
        this.telephoneAgent = telephoneAgent;
    }

    public String getFonctionAgent() {
        return fonctionAgent;
    }

    public void setFonctionAgent(String fonctionAgent) {
        this.fonctionAgent = fonctionAgent;
    }

    public String getGradeAgent() {
        return gradeAgent;
    }

    public void setGradeAgent(String gradeAgent) {
        this.gradeAgent = gradeAgent;
    }

    public Long getIdStructureAgent() {
        return idStructureAgent;
    }

    public void setIdStructureAgent(Long idStructureAgent) {
        this.idStructureAgent = idStructureAgent;
    }

    public Integer getStatutActifAgent() {
        return statutActifAgent;
    }

    public void setStatutActifAgent(Integer statutActifAgent) {
        this.statutActifAgent = statutActifAgent;
    }

    public Date getDateCreationAgent() {
        return dateCreationAgent;
    }

    public void setDateCreationAgent(Date dateCreationAgent) {
        this.dateCreationAgent = dateCreationAgent;
    }

    public String getEntiteCode() {
        return entiteCode;
    }

    public void setEntiteCode(String entiteCode) {
        this.entiteCode = entiteCode;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
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

    public Set<GmRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<GmRole> roles) {
        this.roles = roles;
    }
}
