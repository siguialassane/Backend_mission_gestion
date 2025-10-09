package com.example.Gestion_mission.mapper;

import com.example.Gestion_mission.dto.OrdreMissionDTO;
import com.example.Gestion_mission.model.GmOrdreMission;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper pour convertir entre GmOrdreMission et OrdreMissionDTO
 */
@Component
public class OrdreMissionMapper {

    /**
     * Convertit une entité GmOrdreMission en DTO
     */
    public OrdreMissionDTO toDTO(GmOrdreMission entity) {
        if (entity == null) {
            return null;
        }

        return new OrdreMissionDTO(
            entity.getIdOrdreMission(),
            entity.getCodeMission(),
            entity.getObjetMission(),
            entity.getDateDebutMission(),
            entity.getDateFinMission(),
            entity.getLieuDepart(),
            entity.getLieuDestination(),
            entity.getMotifMission(),
            entity.getFraisEstime(),
            entity.getIdAgentCreateur(),
            entity.getStatutMission(),
            entity.getDateCreation(),
            entity.getDateMiseAJour()
        );
    }

    /**
     * Convertit une liste d'entités en liste de DTOs
     */
    public List<OrdreMissionDTO> toDTOList(List<GmOrdreMission> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convertit un DTO en entité GmOrdreMission
     */
    public GmOrdreMission toEntity(OrdreMissionDTO dto) {
        if (dto == null) {
            return null;
        }

        GmOrdreMission entity = new GmOrdreMission();
        entity.setIdOrdreMission(dto.getIdOrdreMission());
        entity.setCodeMission(dto.getCodeMission());
        entity.setObjetMission(dto.getObjetMission());
        entity.setDateDebutMission(dto.getDateDebutMission());
        entity.setDateFinMission(dto.getDateFinMission());
        entity.setLieuDepart(dto.getLieuDepart());
        entity.setLieuDestination(dto.getLieuDestination());
        entity.setMotifMission(dto.getMotifMission());
        entity.setFraisEstime(dto.getFraisEstime());
        entity.setIdAgentCreateur(dto.getIdAgentCreateur());
        entity.setStatutMission(dto.getStatutMission());
        entity.setDateCreation(dto.getDateCreation());
        entity.setDateMiseAJour(dto.getDateMiseAJour());

        return entity;
    }
}
