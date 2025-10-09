package com.example.Gestion_mission.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Identifiant composite liant un rôle et un menu.
 */
@Embeddable
public class GmRoleMenuId implements Serializable {

    @Column(name = "ID_ROLE")
    private Long idRole;

    @Column(name = "ID_MENU")
    private Long idMenu;

    public GmRoleMenuId() {
    }

    public GmRoleMenuId(Long idRole, Long idMenu) {
        this.idRole = idRole;
        this.idMenu = idMenu;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GmRoleMenuId that = (GmRoleMenuId) o;
        return Objects.equals(idRole, that.idRole) && Objects.equals(idMenu, that.idMenu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRole, idMenu);
    }
}
