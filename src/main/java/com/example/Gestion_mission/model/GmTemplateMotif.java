package com.example.Gestion_mission.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "GM_TEMPLATE_MOTIF")
public class GmTemplateMotif {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "template_motif_seq")
    @SequenceGenerator(name = "template_motif_seq", sequenceName = "SEQ_GM_TEMPLATE_MOTIF", allocationSize = 1)
    @Column(name = "ID_TEMPLATE")
    private Long idTemplate;

    @Column(name = "ID_UTILISATEUR_CREATEUR", nullable = false)
    private Long idUtilisateurCreateur;

    @Column(name = "NOM_TEMPLATE", nullable = false, length = 200)
    private String nomTemplate;

    @Lob
    @Column(name = "CONTENU_TEMPLATE", nullable = false)
    private String contenuTemplate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATION")
    private Date dateCreation;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_MODIFICATION")
    private Date dateModification;

    // Constructeurs
    public GmTemplateMotif() {
        this.dateCreation = new Date();
    }

    // Getters et Setters
    public Long getIdTemplate() {
        return idTemplate;
    }

    public void setIdTemplate(Long idTemplate) {
        this.idTemplate = idTemplate;
    }

    public Long getIdUtilisateurCreateur() {
        return idUtilisateurCreateur;
    }

    public void setIdUtilisateurCreateur(Long idUtilisateurCreateur) {
        this.idUtilisateurCreateur = idUtilisateurCreateur;
    }

    public String getNomTemplate() {
        return nomTemplate;
    }

    public void setNomTemplate(String nomTemplate) {
        this.nomTemplate = nomTemplate;
    }

    public String getContenuTemplate() {
        return contenuTemplate;
    }

    public void setContenuTemplate(String contenuTemplate) {
        this.contenuTemplate = contenuTemplate;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    @PreUpdate
    public void preUpdate() {
        this.dateModification = new Date();
    }
}
