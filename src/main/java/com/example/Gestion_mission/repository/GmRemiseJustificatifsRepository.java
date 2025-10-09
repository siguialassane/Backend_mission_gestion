package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.GmRemiseJustificatifs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GmRemiseJustificatifsRepository extends JpaRepository<GmRemiseJustificatifs, Long> {

    Optional<GmRemiseJustificatifs> findByOrdreMissionIdOrdreMission(Long idOrdreMission);
}
