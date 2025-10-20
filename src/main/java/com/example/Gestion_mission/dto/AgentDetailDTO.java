package com.example.Gestion_mission.dto;

public class AgentDetailDTO {
    private Long id;
    private String matricule;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String fonction;
    private String grade;
    private Integer statutActif;

    public AgentDetailDTO(Long id, String matricule, String nom, String prenom, 
                          String email, String telephone, String fonction, 
                          String grade, Integer statutActif) {
        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.fonction = fonction;
        this.grade = grade;
        this.statutActif = statutActif;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getFonction() { return fonction; }
    public void setFonction(String fonction) { this.fonction = fonction; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public Integer getStatutActif() { return statutActif; }
    public void setStatutActif(Integer statutActif) { this.statutActif = statutActif; }
}
