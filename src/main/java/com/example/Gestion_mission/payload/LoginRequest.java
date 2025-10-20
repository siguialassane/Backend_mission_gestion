package com.example.Gestion_mission.payload;

public class LoginRequest {
    // ANCIEN (garder pour logs)
    private String email;
    
    // NOUVEAU - Identifiant de connexion (NOM PRENOM en UPPERCASE)
    private String identifiantLogin;
    
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentifiantLogin() {
        return identifiantLogin;
    }

    public void setIdentifiantLogin(String identifiantLogin) {
        this.identifiantLogin = identifiantLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
