package com.example.Gestion_mission.service;

import com.example.Gestion_mission.model.GmRoleMenu;
import com.example.Gestion_mission.model.GmRoleMenuId;
import com.example.Gestion_mission.repository.GmMenuRepository;
import com.example.Gestion_mission.repository.GmRoleMenuRepository;
import com.example.Gestion_mission.repository.GmRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleMenuService {

    private static final Logger log = LoggerFactory.getLogger(RoleMenuService.class);

    private final GmRoleMenuRepository roleMenuRepository;
    private final GmRoleRepository roleRepository;
    private final GmMenuRepository menuRepository;
    private final JournalUtilisateurService journalService;
    private final RoleService roleService;

    public RoleMenuService(GmRoleMenuRepository roleMenuRepository,
                           GmRoleRepository roleRepository,
                           GmMenuRepository menuRepository,
                           JournalUtilisateurService journalService,
                           RoleService roleService) {
        this.roleMenuRepository = roleMenuRepository;
        this.roleRepository = roleRepository;
        this.menuRepository = menuRepository;
        this.journalService = journalService;
        this.roleService = roleService;
    }

    @Transactional(readOnly = true)
    public List<GmRoleMenu> recupererDroitsParRole(Long idRole) {
        log.debug("Lecture des droits pour le rôle {}", idRole);
        return roleMenuRepository.findByRoleIdRole(idRole);
    }

    @Transactional
    public GmRoleMenu mettreAJourDroits(Long idRole, Long idMenu, Integer peutConsulter, Integer peutModifier, Integer peutValider) {
        var role = roleRepository.findById(idRole)
                .orElseThrow(() -> new IllegalArgumentException("Rôle introuvable : " + idRole));
        var menu = menuRepository.findById(idMenu)
                .orElseThrow(() -> new IllegalArgumentException("Menu introuvable : " + idMenu));

        GmRoleMenuId id = new GmRoleMenuId(idRole, idMenu);
        GmRoleMenu roleMenu = roleMenuRepository.findById(id)
                .orElseGet(() -> {
                    GmRoleMenu nouveau = new GmRoleMenu();
                    nouveau.setId(id);
                    nouveau.setRole(role);
                    nouveau.setMenu(menu);
                    return nouveau;
                });

        roleMenu.setPeutConsulter(peutConsulter);
        roleMenu.setPeutModifier(peutModifier);
        roleMenu.setPeutValider(peutValider);

        log.info("Mise à jour des droits pour rôle {} et menu {}", idRole, idMenu);
        journalService.enregistrerActionModification(
                roleService.getIdUtilisateurConnecte(),
                "GM_ROLE_MENU",
                "Ancienne configuration",
                "Role=" + idRole + ",Menu=" + idMenu + ",Consulter=" + peutConsulter,
                "127.0.0.1",
                "SpringBoot"
        );

        return roleMenuRepository.save(roleMenu);
    }
}
