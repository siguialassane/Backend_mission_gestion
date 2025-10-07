package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.GmOrdreMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GmOrdreMissionRepository extends JpaRepository<GmOrdreMission, Long> {
}