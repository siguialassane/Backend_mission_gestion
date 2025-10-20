package com.example.Gestion_mission.service;

import com.example.Gestion_mission.model.UtilisateurService;
import com.example.Gestion_mission.repository.UtilisateurServiceRepository;
import com.example.Gestion_mission.security.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UtilisateurServiceRepository utilisateurServiceRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("🔐 [AUTH] Tentative de chargement utilisateur avec identifiant: {}", username);
        
        if (username == null || username.isBlank()) {
            logger.error("❌ [AUTH] Identifiant NULL ou vide reçu!");
            throw new UsernameNotFoundException("Identifiant vide");
        }

        try {
            // Chercher par IDENTIFIANT_LOGIN (NOM PRENOM en UPPERCASE)
            logger.debug("[AUTH] Recherche dans BD par IDENTIFIANT_LOGIN: {}", username);
            UtilisateurService user = utilisateurServiceRepository.findByIdentifiantLogin(username)
                    .orElseThrow(() -> {
                        logger.warn("⚠️ [AUTH] Utilisateur introuvable pour identifiant: {}", username);
                        return new UsernameNotFoundException("Utilisateur non trouvé: " + username);
                    });

            // Vérifier que l'utilisateur est actif (DELETED = 0)
            if (user.getDeleted() != null && user.getDeleted() == 1) {
                logger.error("❌ [AUTH] Utilisateur {} est supprimé (DELETED=1)", user.getIdUtilisateur());
                throw new UsernameNotFoundException("Utilisateur désactivé");
            }

            logger.debug("[AUTH] Utilisateur trouvé - ID: {}, Email: {}", user.getIdUtilisateur(), user.getEmail());

            // Vérifier que l'utilisateur a exactement 1 rôle
            if (user.getRoles() == null || user.getRoles().isEmpty()) {
                logger.error("❌ [AUTH] Utilisateur {} n'a AUCUN rôle assigné!", user.getIdUtilisateur());
                throw new RuntimeException("Utilisateur sans rôle - configuration invalide");
            }

            if (user.getRoles().size() != 1) {
                logger.error("❌ [AUTH] Utilisateur {} a {} rôles au lieu de 1! Rôles: {}", 
                    user.getIdUtilisateur(), 
                    user.getRoles().size(),
                    user.getRoles().stream().map(r -> r.getNomRole()).toList()
                );
                throw new RuntimeException("Utilisateur avec configuration rôles invalide (" + user.getRoles().size() + " rôles)");
            }

            String roleNom = user.getRoles().iterator().next().getNomRole();
            logger.debug("[AUTH] Utilisateur {} a 1 rôle: {}", user.getIdUtilisateur(), roleNom);
            logger.info("✅ [AUTH] Authentification réussie - User: {} (ID: {})", username, user.getIdUtilisateur());

            return UserDetailsImpl.build(user);
            
        } catch (UsernameNotFoundException e) {
            logger.error("❌ [AUTH] UsernameNotFoundException: {}", e.getMessage());
            throw e;
        } catch (RuntimeException e) {
            logger.error("❌ [AUTH] RuntimeException lors de la validation des rôles: {}", e.getMessage());
            throw new UsernameNotFoundException("Configuration utilisateur invalide: " + e.getMessage());
        } catch (Exception e) {
            logger.error("❌ [AUTH] Exception inattendue lors du chargement utilisateur: ", e);
            throw new UsernameNotFoundException("Erreur système lors de l'authentification");
        }
    }
}