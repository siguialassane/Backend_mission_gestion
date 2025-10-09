package com.example.Gestion_mission.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Représente un écran ou une fonctionnalité accessible via les menus de l'application.
 */
@Entity
@Table(name = "GM_MENU")
public class GmMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gm_menu")
    @SequenceGenerator(name = "seq_gm_menu", sequenceName = "SEQ_GM_MENU", allocationSize = 1)
    @Column(name = "ID_MENU")
    private Long idMenu;

    @Column(name = "CODE_MENU", nullable = false, unique = true)
    private String codeMenu;

    @Column(name = "LIBELLE_MENU")
    private String libelleMenu;

    @Column(name = "DESCRIPTION_MENU")
    private String descriptionMenu;

    @Column(name = "URL_MENU")
    private String urlMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_MENU_ID")
    private GmMenu parentMenu;

    @OneToMany(mappedBy = "parentMenu", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<GmMenu> sousMenus = new HashSet<>();

    @Column(name = "ACTIF")
    private Integer actif;

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

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public String getCodeMenu() {
        return codeMenu;
    }

    public void setCodeMenu(String codeMenu) {
        this.codeMenu = codeMenu;
    }

    public String getLibelleMenu() {
        return libelleMenu;
    }

    public void setLibelleMenu(String libelleMenu) {
        this.libelleMenu = libelleMenu;
    }

    public String getDescriptionMenu() {
        return descriptionMenu;
    }

    public void setDescriptionMenu(String descriptionMenu) {
        this.descriptionMenu = descriptionMenu;
    }

    public String getUrlMenu() {
        return urlMenu;
    }

    public void setUrlMenu(String urlMenu) {
        this.urlMenu = urlMenu;
    }

    public GmMenu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(GmMenu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public Set<GmMenu> getSousMenus() {
        return sousMenus;
    }

    public void setSousMenus(Set<GmMenu> sousMenus) {
        this.sousMenus = sousMenus;
    }

    public Integer getActif() {
        return actif;
    }

    public void setActif(Integer actif) {
        this.actif = actif;
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
