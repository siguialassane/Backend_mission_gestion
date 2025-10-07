package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.GmFraisMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GmFraisMissionRepository extends JpaRepository<GmFraisMission, Long> {
    List<GmFraisMission> findByIdOrdreMission(Long idOrdreMission);
}