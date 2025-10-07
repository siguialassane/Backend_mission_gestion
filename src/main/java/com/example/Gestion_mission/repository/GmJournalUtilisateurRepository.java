package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.GmJournalUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GmJournalUtilisateurRepository extends JpaRepository<GmJournalUtilisateur, Long> {
}