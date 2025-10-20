package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.GmMissionAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GmMissionAgentRepository extends JpaRepository<GmMissionAgent, Long> {

    List<GmMissionAgent> findByOrdreMissionIdOrdreMission(Long missionId);

    boolean existsByOrdreMissionIdOrdreMissionAndAgentIdAgent(Long missionId, Long agentId);

    @Query(value = "SELECT CASE WHEN COUNT(1) > 0 THEN 1 ELSE 0 END FROM GM_MISSION_AGENT WHERE ID_ORDRE_MISSION = :missionId AND ID_AGENT = :agentId", nativeQuery = true)
    int existsAssociation(@Param("missionId") Long missionId, @Param("agentId") Long agentId);
}
