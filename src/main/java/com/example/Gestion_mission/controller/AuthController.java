package com.example.Gestion_mission.controller;

import com.example.Gestion_mission.dto.LoginResponse;
import com.example.Gestion_mission.model.GmAgent;
import com.example.Gestion_mission.model.GmRole;
import com.example.Gestion_mission.model.UtilisateurService;
import com.example.Gestion_mission.payload.LoginRequest;
import com.example.Gestion_mission.payload.MessageResponse;
import com.example.Gestion_mission.payload.SignupRequest;
import com.example.Gestion_mission.repository.GmAgentRepository;
import com.example.Gestion_mission.repository.GmRoleRepository;
import com.example.Gestion_mission.repository.UtilisateurServiceRepository;
import com.example.Gestion_mission.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    GmAgentRepository gmAgentRepository;

    @Autowired
    GmRoleRepository gmRoleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UtilisateurServiceRepository utilisateurServiceRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        logger.info("🔐 [LOGIN] Tentative de connexion - Identifiant: {}", loginRequest.getIdentifiantLogin());
        
        try {
            // Validation input
            if (loginRequest.getIdentifiantLogin() == null || loginRequest.getIdentifiantLogin().isBlank()) {
                logger.error("❌ [LOGIN] Identifiant vide!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("message", "Identifiant requis"));
            }

            if (loginRequest.getPassword() == null || loginRequest.getPassword().isBlank()) {
                logger.error("❌ [LOGIN] Mot de passe vide!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("message", "Mot de passe requis"));
            }

            logger.debug("[LOGIN] Authentification avec identifiant: {}", loginRequest.getIdentifiantLogin());
            
            // Authentifier avec IDENTIFIANT_LOGIN
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                        loginRequest.getIdentifiantLogin(), 
                        loginRequest.getPassword()
                    )
            );

            logger.debug("[LOGIN] Authentification réussie");
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // IMPORTANT : Sauvegarder explicitement le SecurityContext dans la session HTTP
            HttpSession session = request.getSession(true);
            HttpSessionSecurityContextRepository repo = new HttpSessionSecurityContextRepository();
            repo.saveContext(SecurityContextHolder.getContext(), request, null);
            logger.debug("[LOGIN] Session sauvegardée - ID: {}", session.getId());

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> rolesList = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            logger.debug("[LOGIN] Rôles chargés: {} (nombre: {})", rolesList, rolesList.size());

            // Validation: 1 rôle unique
            if (rolesList.size() != 1) {
                logger.error("❌ [LOGIN] Utilisateur {} a {} rôles au lieu de 1!", 
                    userDetails.getId(), rolesList.size());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("message", "Configuration utilisateur invalide"));
            }

            String roleUnique = rolesList.get(0);
            logger.info("✅ [LOGIN] Authentification réussie - User ID: {} - Rôle: {} - Session: {}", 
                userDetails.getId(), roleUnique, session.getId());

            // Récupérer nom et prenom depuis UtilisateurService
            UtilisateurService utilisateur = utilisateurServiceRepository.findById((long) userDetails.getId())
                    .orElse(null);
            
            String nom = null;
            String prenom = null;
            if (utilisateur != null) {
                nom = utilisateur.getNomUtilisateur();
                prenom = utilisateur.getPrenomUtilisateur();
                logger.debug("[LOGIN] Données utilisateur récupérées - Nom: {}, Prenom: {}", nom, prenom);
            }

            // Retourner réponse avec RÔLE UNIQUE (pas roles[])
            LoginResponse response = new LoginResponse(
                    userDetails.getId(),
                    userDetails.getEmail(),
                    roleUnique,  // ✅ Rôle unique au lieu de List<String>
                    nom,
                    prenom
            );
            
            logger.debug("[LOGIN] Réponse renvoyée avec succès");
            return ResponseEntity.ok(response);
            
        } catch (AuthenticationException ex) {
            logger.warn("⚠️ [LOGIN] Echec d'authentification - Identifiant: {} - Erreur: {}", 
                loginRequest.getIdentifiantLogin(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Identifiant ou mot de passe incorrect"));
        } catch (Exception ex) {
            logger.error("❌ [LOGIN] Exception inattendue: ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erreur système lors de l'authentification"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            logger.info("Déconnexion - Invalidation de la session: {}", session.getId());
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(Map.of("message", "Déconnexion réussie"));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        logger.info("Demande d'inscription pour l'email: {}", signUpRequest.getEmail());
        if (gmAgentRepository.existsByEmailAgent(signUpRequest.getEmail())) {
            logger.warn("Inscription refusée, email déjà utilisé: {}", signUpRequest.getEmail());
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        GmAgent agent = new GmAgent();
        agent.setNomAgent(signUpRequest.getNom());
        agent.setPrenomAgent(signUpRequest.getPrenom());
        agent.setMatriculeAgent(signUpRequest.getMatricule());
        agent.setEmailAgent(signUpRequest.getEmail());
        agent.setMotDePasse(encoder.encode(signUpRequest.getPassword()));
        agent.setStatutActifAgent(1); // Default to active
        agent.setDateCreationAgent(new Date()); // Set creation date

        Set<String> strRoles = signUpRequest.getRole();
        Set<GmRole> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            GmRole gestionnaireRole = gmRoleRepository.findByNomRole("GESTIONNAIRE")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(gestionnaireRole);
        } else {
            strRoles.forEach(role -> {
                switch (role.toLowerCase()) {
                    case "admin":
                        GmRole adminRole = gmRoleRepository.findByNomRole("ADMIN")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "gestionnaire":
                        GmRole gestionnaireRole = gmRoleRepository.findByNomRole("GESTIONNAIRE")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(gestionnaireRole);
                        break;
                    default:
                        GmRole defaultRole = gmRoleRepository.findByNomRole("GESTIONNAIRE")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(defaultRole);
                }
            });
        }

        agent.setRoles(roles);
        gmAgentRepository.save(agent);
        logger.info("Utilisateur créé avec succès, ID: {}", agent.getIdAgent());

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}