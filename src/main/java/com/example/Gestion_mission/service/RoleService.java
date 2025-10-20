package com.example.Gestion_mission.service;

import com.example.Gestion_mission.security.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

    /**
     * Simplifié: 1 utilisateur = 1 rôle unique
     * Pas besoin de vérifications complexes
     */

    public boolean utilisateurEstChefService() {
        return hasRole("CHEF_SERVICE");
    }

    public boolean utilisateurEstRh() {
        return hasRole("RH");
    }

    public boolean utilisateurEstMoyensGeneraux() {
        return hasRole("MOYENS_GENERAUX");
    }

    public boolean utilisateurEstCaisse() {
        return hasRole("CAISSE");
    }

    /**
     * Vérifier si utilisateur a un rôle spécifique
     */
    private boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null) {
            logger.debug("[ROLE] Aucune authentification trouvée");
            return false;
        }
        
        if (!(authentication.getPrincipal() instanceof UserDetailsImpl)) {
            logger.debug("[ROLE] Principal n'est pas UserDetailsImpl");
            return false;
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        
        List<String> roles = userDetails.getAuthorities().stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.toList());
        
        boolean hasRole = roles.contains(role);
        logger.debug("[ROLE] Vérification rôle {} pour user {}: {}", role, userDetails.getId(), hasRole);
        
        return hasRole;
    }
    
    /**
     * Retourner le rôle UNIQUE de l'utilisateur (pas de priorité, il n'y a qu'1 rôle)
     * @return Rôle de l'utilisateur connecté
     */
    public String getRoleUtilisateur() {
        logger.debug("[ROLE] Récupération du rôle utilisateur");
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null) {
            logger.warn("⚠️ [ROLE] Pas d'authentification trouvée");
            return null;
        }
        
        if (!(authentication.getPrincipal() instanceof UserDetailsImpl)) {
            logger.warn("⚠️ [ROLE] Principal n'est pas UserDetailsImpl");
            return null;
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        
        List<String> roles = userDetails.getAuthorities().stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.toList());
        
        if (roles.isEmpty()) {
            logger.error("❌ [ROLE] Utilisateur {} SANS RÔLE!", userDetails.getId());
            return null;
        }

        if (roles.size() != 1) {
            logger.error("❌ [ROLE] Utilisateur {} a {} rôles au lieu de 1!", userDetails.getId(), roles.size());
            roles.forEach(r -> logger.error("  - Rôle: {}", r));
        }

        String roleUnique = roles.get(0);
        logger.info("✅ [ROLE] Rôle utilisateur {}: {}", userDetails.getId(), roleUnique);
        
        return roleUnique;
    }
    
    /**
     * Retourner l'ID de l'utilisateur actuellement connecté
     * @return ID utilisateur
     */
    public Long getIdUtilisateurConnecte() {
        logger.debug("[ROLE] Récupération de l'ID utilisateur connecté");
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null) {
            logger.warn("⚠️ [ROLE] Pas d'authentification");
            return null;
        }
        
        if (!(authentication.getPrincipal() instanceof UserDetailsImpl)) {
            logger.warn("⚠️ [ROLE] Principal n'est pas UserDetailsImpl");
            return null;
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId();
        
        logger.debug("[ROLE] User connecté - ID: {}", userId);
        return userId;
    }
}