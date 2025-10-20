package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.UtilisateurService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurServiceRepository extends JpaRepository<UtilisateurService, Long> {
    
    // ANCIEN - Garder pour compatibilité
    Optional<UtilisateurService> findByEmail(String email);
    Boolean existsByEmail(String email);
    
    // NOUVEAU - Chercher par IDENTIFIANT_LOGIN (NOM PRENOM)
    @Query("SELECT u FROM UtilisateurService u WHERE u.identifiantLogin = :identifiantLogin")
    Optional<UtilisateurService> findByIdentifiantLogin(@Param("identifiantLogin") String identifiantLogin);
}
