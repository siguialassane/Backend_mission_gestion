package com.example.Gestion_mission.service;

import com.example.Gestion_mission.model.GmMissionRessource;
import com.example.Gestion_mission.model.GmOrdreMission;
import com.example.Gestion_mission.model.GmRessource;
import com.example.Gestion_mission.repository.GmMissionRessourceRepository;
import com.example.Gestion_mission.repository.GmOrdreMissionRepository;
import com.example.Gestion_mission.repository.GmRessourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MissionRessourceService {

    private static final Logger log = LoggerFactory.getLogger(MissionRessourceService.class);

    private final GmMissionRessourceRepository missionRessourceRepository;
    private final GmOrdreMissionRepository ordreMissionRepository;
    private final GmRessourceRepository ressourceRepository;
    private final JournalUtilisateurService journalService;
    private final RoleService roleService;

    public MissionRessourceService(GmMissionRessourceRepository missionRessourceRepository,
                                   GmOrdreMissionRepository ordreMissionRepository,
                                   GmRessourceRepository ressourceRepository,
                                   JournalUtilisateurService journalService,
                                   RoleService roleService) {
        this.missionRessourceRepository = missionRessourceRepository;
        this.ordreMissionRepository = ordreMissionRepository;
        this.ressourceRepository = ressourceRepository;
        this.journalService = journalService;
        this.roleService = roleService;
    }

    @Transactional(readOnly = true)
    public List<GmMissionRessource> listerRessourcesMission(Long idOrdreMission) {
        log.info("Consultation des ressources pour la mission {}", idOrdreMission);
        journalService.enregistrerActionConsultation(roleService.getIdUtilisateurConnecte(), "GM_MISSION_RESSOURCE", "127.0.0.1", "SpringBoot");
        return missionRessourceRepository.findByOrdreMissionIdOrdreMission(idOrdreMission);
    }

    @Transactional
    public GmMissionRessource ajouterRessource(Long idOrdreMission, Long idRessource, Integer quantite, String unite, String commentaire) {
        GmOrdreMission ordreMission = ordreMissionRepository.findById(idOrdreMission)
                .orElseThrow(() -> new IllegalArgumentException("Mission introuvable: " + idOrdreMission));
        GmRessource ressource = ressourceRepository.findById(idRessource)
                .orElseThrow(() -> new IllegalArgumentException("Ressource introuvable: " + idRessource));

        GmMissionRessource missionRessource = new GmMissionRessource();
        missionRessource.setOrdreMission(ordreMission);
        missionRessource.setRessource(ressource);
        missionRessource.setQuantite(quantite);
        missionRessource.setUnite(unite);
        missionRessource.setCommentaire(commentaire);

        log.info("Ajout de la ressource {} pour la mission {}", idRessource, idOrdreMission);
        journalService.enregistrerActionCreation(roleService.getIdUtilisateurConnecte(), "GM_MISSION_RESSOURCE",
                "Mission=" + idOrdreMission + ",Ressource=" + idRessource,
                "127.0.0.1", "SpringBoot");

        return missionRessourceRepository.save(missionRessource);
    }

    @Transactional
    public void supprimerRessource(Long idMissionRessource) {
        missionRessourceRepository.findById(idMissionRessource).ifPresent(ressource -> {
            log.warn("Suppression de la ressource {} pour la mission {}", ressource.getRessource().getIdRessource(), ressource.getOrdreMission().getIdOrdreMission());
            journalService.enregistrerActionSuppression(roleService.getIdUtilisateurConnecte(), "GM_MISSION_RESSOURCE",
                    "Mission=" + ressource.getOrdreMission().getIdOrdreMission() + ",Ressource=" + ressource.getRessource().getIdRessource(),
                    "127.0.0.1", "SpringBoot");
            missionRessourceRepository.delete(ressource);
        });
    }
}
