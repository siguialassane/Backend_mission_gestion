package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.GmValidationWorkflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GmValidationWorkflowRepository extends JpaRepository<GmValidationWorkflow, Long> {

    List<GmValidationWorkflow> findByOrdreMissionIdOrdreMissionOrderByOrdreValidationAsc(Long idOrdreMission);

    Optional<GmValidationWorkflow> findByOrdreMissionIdOrdreMissionAndEtapeCodeEtape(Long idOrdreMission, String codeEtape);
}
