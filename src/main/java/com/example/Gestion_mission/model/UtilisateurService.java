package com.example.Gestion_mission.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "UTILISATEUR_SERVICE")
public class UtilisateurService {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utilisateur_service_seq")
    @SequenceGenerator(name = "utilisateur_service_seq", sequenceName = "SEQ_UTILISATEUR_SERVICE", allocationSize = 1)
    @Column(name = "ID_UTILISATEUR")
    private Long idUtilisateur;

    @Column(name = "IDENTIFIANT_LOGIN", nullable = false, unique = true)
    private String identifiantLogin;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "MOT_DE_PASSE", nullable = false)
    private String motDePasse;

    @Column(name = "NOM_UTILISATEUR")
    private String nomUtilisateur;

    @Column(name = "PRENOM_UTILISATEUR")
    private String prenomUtilisateur;

    @Column(name = "STATUT_ACTIF")
    private Integer statutActif;

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
    @JoinTable(
        name = "UTILISATEUR_SERVICE_ROLE",
        joinColumns = @JoinColumn(name = "ID_UTILISATEUR"),
        inverseJoinColumns = @JoinColumn(name = "ID_ROLE")
    )
    private Set<GmRole> roles = new HashSet<>();

    // Constructors
    public UtilisateurService() {
    }

    public UtilisateurService(String email, String motDePasse, String nomUtilisateur, String prenomUtilisateur) {
        this.email = email;
        this.motDePasse = motDePasse;
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.statutActif = 1;
    }

    // Getters and Setters
    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getIdentifiantLogin() {
        return identifiantLogin;
    }

    public void setIdentifiantLogin(String identifiantLogin) {
        this.identifiantLogin = identifiantLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public Integer getStatutActif() {
        return statutActif;
    }

    public void setStatutActif(Integer statutActif) {
        this.statutActif = statutActif;
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
