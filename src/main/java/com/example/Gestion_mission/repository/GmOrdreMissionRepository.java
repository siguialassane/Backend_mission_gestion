package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.GmOrdreMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GmOrdreMissionRepository extends JpaRepository<GmOrdreMission, Long> {
    List<GmOrdreMission> findByStatutMissionIn(List<String> statuts);
    Optional<GmOrdreMission> findFirstByIdOrdreMission(Long idOrdreMission);
    List<GmOrdreMission> findByIdAgentCreateurOrderByDateCreationDesc(Long idAgentCreateur);
}