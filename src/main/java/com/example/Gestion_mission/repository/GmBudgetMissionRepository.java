package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.GmBudgetMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GmBudgetMissionRepository extends JpaRepository<GmBudgetMission, Long> {
    Optional<GmBudgetMission> findByOrdreMissionIdOrdreMission(Long ordreMissionId);
}
