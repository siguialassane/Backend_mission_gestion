package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.GmMissionEtape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GmMissionEtapeRepository extends JpaRepository<GmMissionEtape, Long> {

    List<GmMissionEtape> findByOrdreMissionIdOrdreMissionOrderByOrdrePassageAsc(Long idOrdreMission);

    boolean existsByOrdreMissionIdOrdreMissionAndOrdrePassage(Long idOrdreMission, Integer ordrePassage);
}
