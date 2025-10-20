package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.GmMissionNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GmMissionNotificationRepository extends JpaRepository<GmMissionNotification, Long> {
    List<GmMissionNotification> findByDestinataireRoleAndStatutNotification(String role, String statut);
    List<GmMissionNotification> findByOrdreMissionIdOrdreMission(Long ordreMissionId);

    @Query(value = "SELECT * FROM (SELECT n.* FROM GM_MISSION_NOTIFICATION n WHERE n.DEST_ROLE = :role ORDER BY n.CREATED_AT DESC) WHERE ROWNUM <= 50", nativeQuery = true)
    List<GmMissionNotification> findRecentForRole(@Param("role") String role);

    @Query(value = "SELECT * FROM (SELECT n.* FROM GM_MISSION_NOTIFICATION n WHERE n.DEST_AGENT = :agentId ORDER BY n.CREATED_AT DESC) WHERE ROWNUM <= 50", nativeQuery = true)
    List<GmMissionNotification> findRecentForAgent(@Param("agentId") Long agentId);
}
