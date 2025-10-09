package com.example.Gestion_mission.service;

import com.example.Gestion_mission.dto.OrdreMissionDTO;
import com.example.Gestion_mission.mapper.OrdreMissionMapper;
import com.example.Gestion_mission.model.GmOrdreMission;
import com.example.Gestion_mission.repository.GmOrdreMissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class GmOrdreMissionService {

    private static final Logger logger = LoggerFactory.getLogger(GmOrdreMissionService.class);

    @Autowired
    private GmOrdreMissionRepository ordreMissionRepository;

    @Autowired
    private JournalUtilisateurService journalService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private OrdreMissionMapper mapper;

    public List<OrdreMissionDTO> getAllOrdresMission() {
        // Enregistrer l'action de consultation
        journalService.enregistrerActionConsultation(
            roleService.getIdUtilisateurConnecte(), 
            "GM_ORDRE_MISSION", 
            "127.0.0.1", // À remplacer par l'IP réelle
            "User-Agent" // À remplacer par le vrai user agent
        );

        Long utilisateurId = roleService.getIdUtilisateurConnecte();
        List<GmOrdreMission> missions = ordreMissionRepository.findAll();
        logger.info("Utilisateur {} récupère {} missions", utilisateurId, missions.size());
        return mapper.toDTOList(missions);
    }

    public Optional<GmOrdreMission> getOrdreMissionById(Long id) {
        // Enregistrer l'action de consultation
        journalService.enregistrerActionConsultation(
            roleService.getIdUtilisateurConnecte(), 
            "GM_ORDRE_MISSION", 
            "127.0.0.1", 
            "User-Agent"
        );
        
        return ordreMissionRepository.findById(id);
    }

    public GmOrdreMission createOrdreMission(GmOrdreMission ordreMission) {
        // Enregistrer l'action de création
        journalService.enregistrerActionCreation(
            roleService.getIdUtilisateurConnecte(), 
            "GM_ORDRE_MISSION", 
            "Code: " + ordreMission.getCodeMission() + ", Objet: " + ordreMission.getObjetMission(), 
            "127.0.0.1", 
            "User-Agent"
        );
        
        // Définir des valeurs par défaut si nécessaire
        ordreMission.setStatutMission("BROUILLON"); // Statut initial
        
        return ordreMissionRepository.save(ordreMission);
    }

    public GmOrdreMission updateOrdreMission(Long id, GmOrdreMission ordreMissionDetails) {
        GmOrdreMission ordreMission = ordreMissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordre de mission non trouvé avec l'ID: " + id));

        // Enregistrer l'action de modification (avant/après)
        String donneesAvant = "Code: " + ordreMission.getCodeMission() + ", Objet: " + ordreMission.getObjetMission() + 
                             ", Début: " + ordreMission.getDateDebutMission() + ", Fin: " + ordreMission.getDateFinMission();
        
        // Mettre à jour les champs
        ordreMission.setCodeMission(ordreMissionDetails.getCodeMission());
        ordreMission.setObjetMission(ordreMissionDetails.getObjetMission());
        ordreMission.setDateDebutMission(ordreMissionDetails.getDateDebutMission());
        ordreMission.setDateFinMission(ordreMissionDetails.getDateFinMission());
        ordreMission.setLieuDepart(ordreMissionDetails.getLieuDepart());
        ordreMission.setLieuDestination(ordreMissionDetails.getLieuDestination());
        ordreMission.setMotifMission(ordreMissionDetails.getMotifMission());
        ordreMission.setFraisEstime(ordreMissionDetails.getFraisEstime());
        ordreMission.setStatutMission(ordreMissionDetails.getStatutMission());

        String donneesApres = "Code: " + ordreMission.getCodeMission() + ", Objet: " + ordreMission.getObjetMission() + 
                             ", Début: " + ordreMission.getDateDebutMission() + ", Fin: " + ordreMission.getDateFinMission();
        
        journalService.enregistrerActionModification(
            roleService.getIdUtilisateurConnecte(), 
            "GM_ORDRE_MISSION", 
            donneesAvant,
            donneesApres,
            "127.0.0.1", 
            "User-Agent"
        );
        
        return ordreMissionRepository.save(ordreMission);
    }

    public void deleteOrdreMission(Long id) {
        Optional<GmOrdreMission> ordreMission = ordreMissionRepository.findById(id);
        if (ordreMission.isPresent()) {
            // Enregistrer l'action de suppression
            GmOrdreMission mission = ordreMission.get();
            journalService.enregistrerActionSuppression(
                roleService.getIdUtilisateurConnecte(), 
                "GM_ORDRE_MISSION", 
                "Code: " + mission.getCodeMission() + ", Objet: " + mission.getObjetMission(), 
                "127.0.0.1", 
                "User-Agent"
            );
            
            ordreMissionRepository.deleteById(id);
        }
    }
}