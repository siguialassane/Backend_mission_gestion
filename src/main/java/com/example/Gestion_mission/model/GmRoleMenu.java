package com.example.Gestion_mission.model;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Définit les droits d'un rôle sur un menu donné.
 */
@Entity
@Table(name = "GM_ROLE_MENU")
public class GmRoleMenu {

    @EmbeddedId
    private GmRoleMenuId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idRole")
    @JoinColumn(name = "ID_ROLE", nullable = false)
    private GmRole role;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idMenu")
    @JoinColumn(name = "ID_MENU", nullable = false)
    private GmMenu menu;

    @Column(name = "PEUT_CONSULTER")
    private Integer peutConsulter;

    @Column(name = "PEUT_MODIFIER")
    private Integer peutModifier;

    @Column(name = "PEUT_VALIDER")
    private Integer peutValider;

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

    public GmRoleMenuId getId() {
        return id;
    }

    public void setId(GmRoleMenuId id) {
        this.id = id;
    }

    public GmRole getRole() {
        return role;
    }

    public void setRole(GmRole role) {
        this.role = role;
    }

    public GmMenu getMenu() {
        return menu;
    }

    public void setMenu(GmMenu menu) {
        this.menu = menu;
    }

    public Integer getPeutConsulter() {
        return peutConsulter;
    }

    public void setPeutConsulter(Integer peutConsulter) {
        this.peutConsulter = peutConsulter;
    }

    public Integer getPeutModifier() {
        return peutModifier;
    }

    public void setPeutModifier(Integer peutModifier) {
        this.peutModifier = peutModifier;
    }

    public Integer getPeutValider() {
        return peutValider;
    }

    public void setPeutValider(Integer peutValider) {
        this.peutValider = peutValider;
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
