package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.GmValidationEtape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GmValidationEtapeRepository extends JpaRepository<GmValidationEtape, Long> {

    Optional<GmValidationEtape> findByCodeEtape(String codeEtape);

    List<GmValidationEtape> findAllByOrderByOrdreEtapeAsc();
}
