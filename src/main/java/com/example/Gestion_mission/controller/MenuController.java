package com.example.Gestion_mission.controller;

import com.example.Gestion_mission.annotation.JournaliserAction;
import com.example.Gestion_mission.annotation.RoleAutorise;
import com.example.Gestion_mission.model.GmMenu;
import com.example.Gestion_mission.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MenuController {

    private static final Logger log = LoggerFactory.getLogger(MenuController.class);

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    @JournaliserAction(entite = "GM_MENU", action = "READ")
    public ResponseEntity<List<GmMenu>> listerMenus() {
        log.debug("API - Consultation des menus");
        return ResponseEntity.ok(menuService.listerMenusActifs());
    }

    @PostMapping
    @RoleAutorise(roles = {"ADMIN"}, peutModifier = true)
    @JournaliserAction(entite = "GM_MENU", action = "CREATE")
    public ResponseEntity<GmMenu> enregistrerMenu(@RequestBody GmMenu menu) {
        log.info("API - Enregistrement du menu {}", menu.getCodeMenu());
        return ResponseEntity.ok(menuService.enregistrerMenu(menu));
    }
}
