package com.example.Gestion_mission.service;

import com.example.Gestion_mission.model.GmFraisMission;
import com.example.Gestion_mission.repository.GmFraisMissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GmFraisMissionService {

    @Autowired
    private GmFraisMissionRepository fraisMissionRepository;

    @Autowired
    private JournalUtilisateurService journalService;

    @Autowired
    private RoleService roleService;

    public List<GmFraisMission> getAllFraisMission() {
        journalService.enregistrerActionConsultation(
            roleService.getIdUtilisateurConnecte(), 
            "GM_FRAIS_MISSION", 
            "127.0.0.1", 
            "User-Agent"
        );
        
        return fraisMissionRepository.findAll();
    }

    public List<GmFraisMission> getFraisMissionByOrdreMission(Long idOrdreMission) {
        journalService.enregistrerActionConsultation(
            roleService.getIdUtilisateurConnecte(), 
            "GM_FRAIS_MISSION", 
            "127.0.0.1", 
            "User-Agent"
        );
        
        return fraisMissionRepository.findByIdOrdreMission(idOrdreMission);
    }

    public Optional<GmFraisMission> getFraisMissionById(Long id) {
        journalService.enregistrerActionConsultation(
            roleService.getIdUtilisateurConnecte(), 
            "GM_FRAIS_MISSION", 
            "127.0.0.1", 
            "User-Agent"
        );
        
        return fraisMissionRepository.findById(id);
    }

    public GmFraisMission createFraisMission(GmFraisMission fraisMission) {
        journalService.enregistrerActionCreation(
            roleService.getIdUtilisateurConnecte(), 
            "GM_FRAIS_MISSION", 
            "Type: " + fraisMission.getTypeFrais() + ", Montant: " + fraisMission.getMontantFrais(),
            "127.0.0.1", 
            "User-Agent"
        );
        
        return fraisMissionRepository.save(fraisMission);
    }

    public GmFraisMission updateFraisMission(Long id, GmFraisMission fraisMissionDetails) {
        GmFraisMission fraisMission = fraisMissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Frais de mission non trouvé avec l'ID: " + id));

        String donneesAvant = "Type: " + fraisMission.getTypeFrais() + ", Montant: " + fraisMission.getMontantFrais();
        
        fraisMission.setTypeFrais(fraisMissionDetails.getTypeFrais());
        fraisMission.setMontantFrais(fraisMissionDetails.getMontantFrais());
        fraisMission.setDateFrais(fraisMissionDetails.getDateFrais());
        fraisMission.setDescriptionFrais(fraisMissionDetails.getDescriptionFrais());
        fraisMission.setJustificatifFrais(fraisMissionDetails.getJustificatifFrais());

        String donneesApres = "Type: " + fraisMission.getTypeFrais() + ", Montant: " + fraisMission.getMontantFrais();
        
        journalService.enregistrerActionModification(
            roleService.getIdUtilisateurConnecte(), 
            "GM_FRAIS_MISSION", 
            donneesAvant,
            donneesApres,
            "127.0.0.1", 
            "User-Agent"
        );
        
        return fraisMissionRepository.save(fraisMission);
    }

    public void deleteFraisMission(Long id) {
        Optional<GmFraisMission> fraisMission = fraisMissionRepository.findById(id);
        if (fraisMission.isPresent()) {
            GmFraisMission frais = fraisMission.get();
            journalService.enregistrerActionSuppression(
                roleService.getIdUtilisateurConnecte(), 
                "GM_FRAIS_MISSION", 
                "Type: " + frais.getTypeFrais() + ", Montant: " + frais.getMontantFrais(),
                "127.0.0.1", 
                "User-Agent"
            );
            
            fraisMissionRepository.deleteById(id);
        }
    }
}