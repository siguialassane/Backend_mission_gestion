package com.example.Gestion_mission.controller;

import com.example.Gestion_mission.annotation.JournaliserAction;
import com.example.Gestion_mission.annotation.RoleAutorise;
import com.example.Gestion_mission.model.GmRoleMenu;
import com.example.Gestion_mission.service.RoleMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RoleMenuController {

    private static final Logger log = LoggerFactory.getLogger(RoleMenuController.class);

    private final RoleMenuService roleMenuService;

    public RoleMenuController(RoleMenuService roleMenuService) {
        this.roleMenuService = roleMenuService;
    }

    @GetMapping("/{roleId}/menus")
    @RoleAutorise(roles = {"ADMIN"})
    @JournaliserAction(entite = "GM_ROLE_MENU", action = "READ")
    public ResponseEntity<List<GmRoleMenu>> recupererMenusParRole(@PathVariable Long roleId) {
        log.debug("API - Lecture des droits pour le rôle {}", roleId);
        return ResponseEntity.ok(roleMenuService.recupererDroitsParRole(roleId));
    }

    @PostMapping("/{roleId}/menus/{menuId}")
    @RoleAutorise(roles = {"ADMIN"}, peutModifier = true)
    @JournaliserAction(entite = "GM_ROLE_MENU", action = "UPDATE")
    public ResponseEntity<GmRoleMenu> mettreAJourDroits(@PathVariable Long roleId,
                                                        @PathVariable Long menuId,
                                                        @RequestBody RoleMenuRequest request) {
        log.info("API - Mise à jour des droits role/menu pour rôle {}", roleId);
        GmRoleMenu roleMenu = roleMenuService.mettreAJourDroits(
                roleId,
                menuId,
                request.peutConsulter(),
                request.peutModifier(),
                request.peutValider()
        );
        return ResponseEntity.ok(roleMenu);
    }

    public record RoleMenuRequest(Integer peutConsulter, Integer peutModifier, Integer peutValider) {
    }
}
