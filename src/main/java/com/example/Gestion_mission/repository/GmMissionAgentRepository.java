package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.GmMissionAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GmMissionAgentRepository extends JpaRepository<GmMissionAgent, Long> {

    List<GmMissionAgent> findByOrdreMissionIdOrdreMission(Long missionId);

    boolean existsByOrdreMissionIdOrdreMissionAndAgentIdAgent(Long missionId, Long agentId);
}
