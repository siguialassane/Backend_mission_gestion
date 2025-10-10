package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.AcVille;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcVilleRepository extends JpaRepository<AcVille, String> {

    Page<AcVille> findByLibelleContainingIgnoreCaseOrLibelleLongContainingIgnoreCase(String libelle,
                                                                                     String libelleLong,
                                                                                     Pageable pageable);
}
