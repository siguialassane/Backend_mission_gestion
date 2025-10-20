package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.AcEntite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcEntiteRepository extends JpaRepository<AcEntite, String> {

    Page<AcEntite> findByLibelleContainingIgnoreCaseOrLibelleLongContainingIgnoreCase(String libelle,
                                                                                      String libelleLong,
                                                                                      Pageable pageable);
}
