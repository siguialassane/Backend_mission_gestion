package com.example.Gestion_mission.dto;

import java.util.Date;

/**
 * Fait le lien entre une mission et la remise des justificatifs.
 */
public class RemiseJustificatifsDTO {

    private Integer recuRapport;
    private Integer recuFacture;
    private Integer recuOrdre;
    private Date dateRemise;
    private String observations;
    private AgentDTO agentRecepteur;

    public Integer getRecuRapport() {
        return recuRapport;
    }

    public void setRecuRapport(Integer recuRapport) {
        this.recuRapport = recuRapport;
    }

    public Integer getRecuFacture() {
        return recuFacture;
    }

    public void setRecuFacture(Integer recuFacture) {
        this.recuFacture = recuFacture;
    }

    public Integer getRecuOrdre() {
        return recuOrdre;
    }

    public void setRecuOrdre(Integer recuOrdre) {
        this.recuOrdre = recuOrdre;
    }

    public Date getDateRemise() {
        return dateRemise;
    }

    public void setDateRemise(Date dateRemise) {
        this.dateRemise = dateRemise;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public AgentDTO getAgentRecepteur() {
        return agentRecepteur;
    }

    public void setAgentRecepteur(AgentDTO agentRecepteur) {
        this.agentRecepteur = agentRecepteur;
    }

    public static class AgentDTO {
        private Long idAgent;
        private String nomAgent;
        private String prenomAgent;

        public Long getIdAgent() {
            return idAgent;
        }

        public void setIdAgent(Long idAgent) {
            this.idAgent = idAgent;
        }

        public String getNomAgent() {
            return nomAgent;
        }

        public void setNomAgent(String nomAgent) {
            this.nomAgent = nomAgent;
        }

        public String getPrenomAgent() {
            return prenomAgent;
        }

        public void setPrenomAgent(String prenomAgent) {
            this.prenomAgent = prenomAgent;
        }
    }
}
