package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.GmRessource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GmRessourceRepository extends JpaRepository<GmRessource, Long> {
}
