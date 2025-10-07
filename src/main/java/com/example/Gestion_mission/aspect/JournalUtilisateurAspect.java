package com.example.Gestion_mission.aspect;

import com.example.Gestion_mission.annotation.JournaliserAction;
import com.example.Gestion_mission.security.UserDetailsImpl;
import com.example.Gestion_mission.service.JournalUtilisateurService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class JournalUtilisateurAspect {

    @Autowired
    private JournalUtilisateurService journalService;

    @After("@annotation(journaliserAction)")
    public void journaliserAction(JoinPoint joinPoint, JournaliserAction journaliserAction) {
        // Récupérer les détails de l'utilisateur connecté
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        Long idAgent = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsImpl) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            idAgent = userDetails.getId();
        }

        // Récupérer l'entité cible et le type d'action
        String entiteCible = journaliserAction.entite();
        String actionEffectuee = journaliserAction.action();
        
        // Récupérer les paramètres de la méthode pour les enregistrer si nécessaire
        String parametres = Arrays.toString(joinPoint.getArgs());
        
        // Récupérer l'adresse IP (simplifié ici, dans une application réelle on récupère du HttpServletRequest)
        String adresseIp = "127.0.0.1"; // À remplacer par l'IP réelle du client
        String userAgent = "User-Agent"; // À remplacer par le vrai user agent

        // Enregistrer l'action dans le journal
        journalService.enregistrerAction(idAgent, entiteCible, actionEffectuee, null, parametres, 
                                         "Exécution de la méthode: " + joinPoint.getSignature().getName(), 
                                         adresseIp, userAgent);
    }
}