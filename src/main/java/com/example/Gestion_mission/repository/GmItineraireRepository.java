package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.GmItineraire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GmItineraireRepository extends JpaRepository<GmItineraire, Long> {
}
