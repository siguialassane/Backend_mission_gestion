package com.example.Gestion_mission.service;

import com.example.Gestion_mission.model.GmMenu;
import com.example.Gestion_mission.repository.GmMenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {

    private static final Logger log = LoggerFactory.getLogger(MenuService.class);

    private final GmMenuRepository gmMenuRepository;

    private final JournalUtilisateurService journalService;

    private final RoleService roleService;

    public MenuService(GmMenuRepository gmMenuRepository,
                       JournalUtilisateurService journalService,
                       RoleService roleService) {
        this.gmMenuRepository = gmMenuRepository;
        this.journalService = journalService;
        this.roleService = roleService;
    }

    @Transactional(readOnly = true)
    public List<GmMenu> listerMenusActifs() {
        log.info("Consultation des menus actifs par l'utilisateur {}", roleService.getIdUtilisateurConnecte());
        journalService.enregistrerActionConsultation(
                roleService.getIdUtilisateurConnecte(),
                "GM_MENU",
                "127.0.0.1",
                "SpringBoot"
        );
        return gmMenuRepository.findAll();
    }

    @Transactional
    public GmMenu enregistrerMenu(GmMenu menu) {
        log.info("Création ou mise à jour d'un menu {} par l'utilisateur {}", menu.getCodeMenu(), roleService.getIdUtilisateurConnecte());
        journalService.enregistrerActionCreation(
                roleService.getIdUtilisateurConnecte(),
                "GM_MENU",
                menu.getCodeMenu(),
                "127.0.0.1",
                "SpringBoot"
        );
        return gmMenuRepository.save(menu);
    }
}
