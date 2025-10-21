package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.GmTemplateMotif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GmTemplateMotifRepository extends JpaRepository<GmTemplateMotif, Long> {
    
    /**
     * Trouver tous les templates créés par un utilisateur spécifique
     */
    List<GmTemplateMotif> findByIdUtilisateurCreateurOrderByDateCreationDesc(Long idUtilisateur);
    
    /**
     * Compter les templates d'un utilisateur
     */
    long countByIdUtilisateurCreateur(Long idUtilisateur);
}
