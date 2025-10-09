package com.example.Gestion_mission.dto;

/**
 * Représente une ressource allouée à une mission côté API.
 */
public class MissionRessourceDTO {

    private Long idMissionRessource;
    private Integer quantite;
    private String unite;
    private String commentaire;
    private RessourceDTO ressource;

    public Long getIdMissionRessource() {
        return idMissionRessource;
    }

    public void setIdMissionRessource(Long idMissionRessource) {
        this.idMissionRessource = idMissionRessource;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public RessourceDTO getRessource() {
        return ressource;
    }

    public void setRessource(RessourceDTO ressource) {
        this.ressource = ressource;
    }

    public static class RessourceDTO {
        private Long idRessource;
        private String libelleRessource;

        public Long getIdRessource() {
            return idRessource;
        }

        public void setIdRessource(Long idRessource) {
            this.idRessource = idRessource;
        }

        public String getLibelleRessource() {
            return libelleRessource;
        }

        public void setLibelleRessource(String libelleRessource) {
            this.libelleRessource = libelleRessource;
        }
    }
}
