package com.example.Gestion_mission.security;

import com.example.Gestion_mission.model.GmAgent;
import com.example.Gestion_mission.model.UtilisateurService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsImpl.class);

    private Long id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    // ANCIEN - Garder pour compatibilité (mais utiliser UtilisateurService désormais)
    @Deprecated
    public static UserDetailsImpl build(GmAgent agent) {
        logger.debug("[DETAILS] Construction UserDetails depuis GmAgent (DEPRECATED)");
        List<GrantedAuthority> authorities = agent.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getNomRole()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                agent.getIdAgent(),
                agent.getEmailAgent(), // Using email as username
                agent.getEmailAgent(),
                agent.getMotDePasse(),
                authorities);
    }

    // NOUVEAU - Méthode principale avec validation 1 rôle
    public static UserDetailsImpl build(UtilisateurService user) {
        logger.debug("[DETAILS] Construction UserDetails depuis UtilisateurService - User ID: {}", user.getIdUtilisateur());
        
        if (user.getRoles() == null) {
            logger.error("❌ [DETAILS] Roles NULL pour utilisateur {}", user.getIdUtilisateur());
            throw new RuntimeException("Utilisateur sans rôles");
        }

        if (user.getRoles().isEmpty()) {
            logger.error("❌ [DETAILS] Roles EMPTY pour utilisateur {}", user.getIdUtilisateur());
            throw new RuntimeException("Utilisateur sans rôles assignés");
        }

        if (user.getRoles().size() != 1) {
            logger.error("❌ [DETAILS] Utilisateur {} a {} rôles au lieu de 1!", 
                user.getIdUtilisateur(), user.getRoles().size());
            user.getRoles().forEach(r -> logger.error("  - Rôle: {}", r.getNomRole()));
            throw new RuntimeException("Utilisateur avec " + user.getRoles().size() + " rôles (attendu: 1)");
        }

        String roleAssigne = user.getRoles().iterator().next().getNomRole();
        logger.info("✅ [DETAILS] Utilisateur {} avec rôle unique: {}", user.getIdUtilisateur(), roleAssigne);

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getNomRole()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getIdUtilisateur(),
                user.getIdentifiantLogin(),  // username = IDENTIFIANT_LOGIN (NOM PRENOM)
                user.getEmail(),
                user.getMotDePasse(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true; // We can use agent.getStatutActifAgent() == 1 here if we pass the agent object
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsImpl that = (UserDetailsImpl) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}