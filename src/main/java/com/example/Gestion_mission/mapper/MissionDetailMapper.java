package com.example.Gestion_mission.mapper;

import com.example.Gestion_mission.dto.*;
import com.example.Gestion_mission.model.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class MissionDetailMapper {

    private final OrdreMissionMapper ordreMissionMapper;

    public MissionDetailMapper(OrdreMissionMapper ordreMissionMapper) {
        this.ordreMissionMapper = ordreMissionMapper;
    }

    public MissionDetailDTO toDetailDTO(GmOrdreMission mission,
                                        List<GmMissionRessource> ressources,
                                        List<GmMissionEtape> etapes,
                                        List<GmValidationWorkflow> workflows,
                                        GmRemiseJustificatifs justificatifs,
                                        GmBudgetMission budget,
                                        List<GmMissionNotification> notifications,
                                        List<GmMissionAgent> participants) {
        MissionDetailDTO dto = new MissionDetailDTO();
        dto.setMission(ordreMissionMapper.toDTO(mission));
        dto.setRessources(mapRessources(ressources));
        dto.setEtapes(mapEtapes(etapes));
        dto.setWorkflow(mapWorkflow(workflows));
        dto.setJustificatifs(mapJustificatifs(justificatifs));
        dto.setBudget(mapBudget(budget));
        dto.setNotifications(mapNotifications(notifications));
        dto.setParticipants(mapParticipants(participants));
        return dto;
    }

    private List<MissionRessourceDTO> mapRessources(List<GmMissionRessource> ressources) {
        if (ressources == null) {
            return Collections.emptyList();
        }
        return ressources.stream()
                .filter(Objects::nonNull)
                .map(this::mapRessource)
                .collect(Collectors.toList());
    }

    private MissionRessourceDTO mapRessource(GmMissionRessource entity) {
        MissionRessourceDTO dto = new MissionRessourceDTO();
        dto.setIdMissionRessource(entity.getIdMissionRessource());
        dto.setQuantite(entity.getQuantite());
        dto.setUnite(entity.getUnite());
        dto.setCommentaire(entity.getCommentaire());

        GmRessource ressource = entity.getRessource();
        if (ressource != null) {
            MissionRessourceDTO.RessourceDTO ressourceDTO = new MissionRessourceDTO.RessourceDTO();
            ressourceDTO.setIdRessource(ressource.getIdRessource());
            ressourceDTO.setLibelleRessource(ressource.getLibelleRessource());
            dto.setRessource(ressourceDTO);
        }

        return dto;
    }

    private List<MissionEtapeDTO> mapEtapes(List<GmMissionEtape> etapes) {
        if (etapes == null) {
            return Collections.emptyList();
        }
        return etapes.stream()
                .filter(Objects::nonNull)
                .map(this::mapEtape)
                .collect(Collectors.toList());
    }

    private MissionEtapeDTO mapEtape(GmMissionEtape entity) {
        MissionEtapeDTO dto = new MissionEtapeDTO();
        dto.setIdMissionEtape(entity.getIdMissionEtape());
        dto.setOrdrePassage(entity.getOrdrePassage());
        dto.setVilleDepartCode(entity.getVilleDepartCode());
        dto.setVilleArriveCode(entity.getVilleArriveCode());
        dto.setDateDepart(entity.getDateDepart());
        dto.setDateArrivee(entity.getDateArrivee());
        dto.setModeTransport(entity.getModeTransport());
        dto.setHebergementPrevu(entity.getHebergementPrevu());
        dto.setCommentaireEtape(entity.getCommentaireEtape());
        return dto;
    }

    private List<ValidationWorkflowDTO> mapWorkflow(List<GmValidationWorkflow> workflows) {
        if (workflows == null) {
            return Collections.emptyList();
        }
        return workflows.stream()
                .filter(Objects::nonNull)
                .map(this::mapWorkflowEntry)
                .collect(Collectors.toList());
    }

    private ValidationWorkflowDTO mapWorkflowEntry(GmValidationWorkflow entity) {
        ValidationWorkflowDTO dto = new ValidationWorkflowDTO();
        dto.setIdValidation(entity.getIdValidation());
        dto.setStatutValidation(entity.getStatutValidation());
        dto.setDecision(entity.getDecision());
        dto.setDateValidation(entity.getDateValidation());
        dto.setCommentaires(entity.getCommentaires());
        dto.setMotifRefus(entity.getMotifRefus());
        dto.setMontantValide(entity.getMontantValide());
        dto.setFondsDisponible(entity.getFondsDisponible());
        dto.setReferencePiece(entity.getReferencePiece());

        GmValidationEtape etape = entity.getEtape();
        if (etape != null) {
            ValidationWorkflowDTO.ValidationEtapeDTO etapeDTO = new ValidationWorkflowDTO.ValidationEtapeDTO();
            etapeDTO.setCodeEtape(etape.getCodeEtape());
            etapeDTO.setLibelleEtape(etape.getLibelleEtape());
            dto.setEtape(etapeDTO);
        }

        return dto;
    }

    private RemiseJustificatifsDTO mapJustificatifs(GmRemiseJustificatifs justificatifs) {
        if (justificatifs == null) {
            return null;
        }

        RemiseJustificatifsDTO dto = new RemiseJustificatifsDTO();
        dto.setRecuRapport(justificatifs.getRecuRapport());
        dto.setRecuFacture(justificatifs.getRecuFacture());
        dto.setRecuOrdre(justificatifs.getRecuOrdre());
        dto.setDateRemise(justificatifs.getDateRemise());
        dto.setObservations(justificatifs.getObservations());

        GmAgent agent = justificatifs.getAgentRecepteur();
        if (agent != null) {
            RemiseJustificatifsDTO.AgentDTO agentDTO = new RemiseJustificatifsDTO.AgentDTO();
            agentDTO.setIdAgent(agent.getIdAgent());
            agentDTO.setNomAgent(agent.getNomAgent());
            agentDTO.setPrenomAgent(agent.getPrenomAgent());
            dto.setAgentRecepteur(agentDTO);
        }

        return dto;
    }

    private BudgetMissionDTO mapBudget(GmBudgetMission budget) {
        if (budget == null) {
            return null;
        }

        BudgetMissionDTO dto = new BudgetMissionDTO();
        dto.setIdBudget(budget.getIdBudget());
        dto.setMontantHebergement(budget.getMontantHebergement());
        dto.setMontantRestauration(budget.getMontantRestauration());
        dto.setMontantTransport(budget.getMontantTransport());
        dto.setMontantCarburant(budget.getMontantCarburant());
        dto.setMontantDivers(budget.getMontantDivers());
        dto.setMontantTotal(budget.getMontantTotal());
        dto.setCommentaire(budget.getCommentaire());
        dto.setCreatedAt(budget.getCreatedAt());
        dto.setCreatedBy(budget.getCreatedBy());
        dto.setUpdatedAt(budget.getUpdatedAt());
        dto.setUpdatedBy(budget.getUpdatedBy());
        return dto;
    }

    private List<MissionNotificationDTO> mapNotifications(List<GmMissionNotification> notifications) {
        if (notifications == null) {
            return Collections.emptyList();
        }

        return notifications.stream()
                .filter(Objects::nonNull)
                .map(this::mapNotification)
                .collect(Collectors.toList());
    }

    private MissionNotificationDTO mapNotification(GmMissionNotification entity) {
        MissionNotificationDTO dto = new MissionNotificationDTO();
        dto.setIdNotification(entity.getIdNotification());
        dto.setOrdreMissionId(entity.getOrdreMission() != null ? entity.getOrdreMission().getIdOrdreMission() : null);
        dto.setDestinataireAgentId(entity.getDestinataireAgent() != null ? entity.getDestinataireAgent().getIdAgent() : null);
        dto.setDestinataireRole(entity.getDestinataireRole());
        dto.setTypeNotification(entity.getTypeNotification());
        dto.setMessageNotification(entity.getMessageNotification());
        dto.setStatutNotification(entity.getStatutNotification());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setLectureAt(entity.getLectureAt());
        return dto;
    }

    private List<MissionParticipantDTO> mapParticipants(List<GmMissionAgent> participants) {
        if (participants == null) {
            return Collections.emptyList();
        }

        return participants.stream()
                .filter(Objects::nonNull)
                .map(this::mapParticipant)
                .collect(Collectors.toList());
    }

    private MissionParticipantDTO mapParticipant(GmMissionAgent entity) {
        MissionParticipantDTO dto = new MissionParticipantDTO();
        dto.setIdMissionAgent(entity.getIdMissionAgent());
        dto.setRoleMission(entity.getRoleMission());

        GmAgent agent = entity.getAgent();
        if (agent != null) {
            dto.setAgentId(agent.getIdAgent());
            dto.setMatricule(agent.getMatriculeAgent());
            dto.setNom(agent.getNomAgent());
            dto.setPrenom(agent.getPrenomAgent());
        }

        return dto;
    }
}
