package com.example.Gestion_mission.service;

import com.example.Gestion_mission.dto.MissionNotificationDTO;
import com.example.Gestion_mission.model.GmMissionNotification;
import com.example.Gestion_mission.repository.GmMissionNotificationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MissionNotificationService {

    private final GmMissionNotificationRepository notificationRepository;
    private final RoleService roleService;

    public MissionNotificationService(GmMissionNotificationRepository notificationRepository,
                                      RoleService roleService) {
        this.notificationRepository = notificationRepository;
        this.roleService = roleService;
    }

    public List<MissionNotificationDTO> listerPourUtilisateurCourant() {
        return chargerNotificationsPourUtilisateur().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public MissionNotificationDTO marquerCommeLue(Long notificationId) {
        GmMissionNotification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("Notification introuvable"));

        if (!"LU".equalsIgnoreCase(notification.getStatutNotification())) {
            notification.setStatutNotification("LU");
            notification.setLectureAt(new Date());
            notificationRepository.save(notification);
        }

        return toDto(notification);
    }

    public void marquerToutCommeLu() {
        List<GmMissionNotification> notifications = chargerNotificationsPourUtilisateur();
        Date maintenant = new Date();
        boolean modification = false;

        for (GmMissionNotification notification : notifications) {
            if (!"LU".equalsIgnoreCase(notification.getStatutNotification())) {
                notification.setStatutNotification("LU");
                notification.setLectureAt(maintenant);
                modification = true;
            }
        }

        if (modification) {
            notificationRepository.saveAll(notifications);
        }
    }

    private List<GmMissionNotification> chargerNotificationsPourUtilisateur() {
        Long agentId = roleService.getIdUtilisateurConnecte();
        String role = roleService.getRoleUtilisateur();

        List<GmMissionNotification> notifications = new ArrayList<>();
        if (agentId != null) {
            notifications.addAll(notificationRepository.findRecentForAgent(agentId));
        }
        if (role != null) {
            notifications.addAll(notificationRepository.findRecentForRole(role));
        }

        Map<Long, GmMissionNotification> dedup = new LinkedHashMap<>();
        for (GmMissionNotification notification : notifications) {
            dedup.putIfAbsent(notification.getIdNotification(), notification);
        }

        return dedup.values().stream()
                .sorted((a, b) -> {
                    Date da = a.getCreatedAt();
                    Date db = b.getCreatedAt();
                    if (da == null && db == null) {
                        return 0;
                    }
                    if (da == null) {
                        return 1;
                    }
                    if (db == null) {
                        return -1;
                    }
                    return db.compareTo(da);
                })
                .limit(50)
                .collect(Collectors.toList());
    }

    private MissionNotificationDTO toDto(GmMissionNotification entity) {
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
}
