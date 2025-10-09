package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.GmMissionRessource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GmMissionRessourceRepository extends JpaRepository<GmMissionRessource, Long> {

    List<GmMissionRessource> findByOrdreMissionIdOrdreMission(Long idOrdreMission);
}
