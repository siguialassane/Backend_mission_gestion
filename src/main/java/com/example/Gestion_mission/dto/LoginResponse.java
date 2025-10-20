package com.example.Gestion_mission.dto;

import java.util.List;

public class LoginResponse {
    private Long userId;
    private String email;
    
    // ANCIEN (garder pour compatibilité)
    private List<String> roles;
    
    // NOUVEAU - Rôle unique (1 seul)
    private String role;
    
    private String nom;
    private String prenom;

    // Ancien constructeur (compatibilité)
    public LoginResponse(Long userId, String email, List<String> roles) {
        this(userId, email, roles, null, null);
    }

    public LoginResponse(Long userId, String email, List<String> roles, String nom, String prenom) {
        this.userId = userId;
        this.email = email;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
    }

    // Nouveau constructeur avec rôle unique
    public LoginResponse(Long userId, String email, String role, String nom, String prenom) {
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
