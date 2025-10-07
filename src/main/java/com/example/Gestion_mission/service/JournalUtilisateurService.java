package com.example.Gestion_mission.service;

import com.example.Gestion_mission.model.GmJournalUtilisateur;
import com.example.Gestion_mission.repository.GmJournalUtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JournalUtilisateurService {

    @Autowired
    private GmJournalUtilisateurRepository journalRepository;

    public void enregistrerAction(Long idAgent, String entiteCible, String actionEffectuee, String donneesAvant, String donneesApres, String commentaires, String adresseIp, String userAgent) {
        GmJournalUtilisateur journal = new GmJournalUtilisateur();
        journal.setIdAgent(idAgent);
        journal.setEntiteCible(entiteCible);
        journal.setActionEffectuee(actionEffectuee);
        journal.setDateAction(new Date());
        journal.setDonneesAvant(donneesAvant);
        journal.setDonneesApres(donneesApres);
        journal.setCommentaires(commentaires);
        journal.setAdresseIp(adresseIp);
        journal.setUserAgent(userAgent);
        journal.setCreatedAt(new Date());

        journalRepository.save(journal);
    }

    public void enregistrerActionCreation(Long idAgent, String entiteCible, String donnees, String adresseIp, String userAgent) {
        enregistrerAction(idAgent, entiteCible, "CREATE", null, donnees, "Création d'un nouvel enregistrement", adresseIp, userAgent);
    }

    public void enregistrerActionModification(Long idAgent, String entiteCible, String donneesAvant, String donneesApres, String adresseIp, String userAgent) {
        enregistrerAction(idAgent, entiteCible, "UPDATE", donneesAvant, donneesApres, "Modification d'un enregistrement", adresseIp, userAgent);
    }

    public void enregistrerActionSuppression(Long idAgent, String entiteCible, String donnees, String adresseIp, String userAgent) {
        enregistrerAction(idAgent, entiteCible, "DELETE", donnees, null, "Suppression d'un enregistrement", adresseIp, userAgent);
    }

    public void enregistrerActionConsultation(Long idAgent, String entiteCible, String adresseIp, String userAgent) {
        enregistrerAction(idAgent, entiteCible, "READ", null, null, "Consultation d'un enregistrement", adresseIp, userAgent);
    }
}