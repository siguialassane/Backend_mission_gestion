package com.example.Gestion_mission.service;

import com.example.Gestion_mission.model.GmItineraire;
import com.example.Gestion_mission.model.GmMissionEtape;
import com.example.Gestion_mission.model.GmOrdreMission;
import com.example.Gestion_mission.repository.GmItineraireRepository;
import com.example.Gestion_mission.repository.GmMissionEtapeRepository;
import com.example.Gestion_mission.repository.GmOrdreMissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MissionEtapeService {

    private static final Logger log = LoggerFactory.getLogger(MissionEtapeService.class);

    private final GmMissionEtapeRepository missionEtapeRepository;
    private final GmOrdreMissionRepository ordreMissionRepository;
    private final GmItineraireRepository itineraireRepository;
    private final JournalUtilisateurService journalService;
    private final RoleService roleService;

    public MissionEtapeService(GmMissionEtapeRepository missionEtapeRepository,
                               GmOrdreMissionRepository ordreMissionRepository,
                               GmItineraireRepository itineraireRepository,
                               JournalUtilisateurService journalService,
                               RoleService roleService) {
        this.missionEtapeRepository = missionEtapeRepository;
        this.ordreMissionRepository = ordreMissionRepository;
        this.itineraireRepository = itineraireRepository;
        this.journalService = journalService;
        this.roleService = roleService;
    }

    @Transactional(readOnly = true)
    public List<GmMissionEtape> listerEtapes(Long idOrdreMission) {
        log.info("Consultation des étapes pour la mission {}", idOrdreMission);
        journalService.enregistrerActionConsultation(roleService.getIdUtilisateurConnecte(), "GM_MISSION_ETAPE", "127.0.0.1", "SpringBoot");
        return missionEtapeRepository.findByOrdreMissionIdOrdreMissionOrderByOrdrePassageAsc(idOrdreMission);
    }

    @Transactional
    public GmMissionEtape ajouterEtape(Long idOrdreMission, GmMissionEtape nouvelleEtape) {
        GmOrdreMission mission = ordreMissionRepository.findById(idOrdreMission)
                .orElseThrow(() -> new IllegalArgumentException("Mission introuvable: " + idOrdreMission));

        if (missionEtapeRepository.existsByOrdreMissionIdOrdreMissionAndOrdrePassage(idOrdreMission, nouvelleEtape.getOrdrePassage())) {
            throw new IllegalArgumentException("Une étape existe déjà à l'ordre " + nouvelleEtape.getOrdrePassage());
        }

        nouvelleEtape.setOrdreMission(mission);

        if (nouvelleEtape.getItineraire() != null && nouvelleEtape.getItineraire().getIdItineraire() != null) {
            GmItineraire itineraire = itineraireRepository.findById(nouvelleEtape.getItineraire().getIdItineraire())
                    .orElseThrow(() -> new IllegalArgumentException("Itinéraire introuvable"));
            nouvelleEtape.setItineraire(itineraire);
        }

        log.info("Ajout d'une étape {} pour la mission {}", nouvelleEtape.getOrdrePassage(), idOrdreMission);
        journalService.enregistrerActionCreation(roleService.getIdUtilisateurConnecte(), "GM_MISSION_ETAPE",
                "Mission=" + idOrdreMission + ",Ordre=" + nouvelleEtape.getOrdrePassage(),
                "127.0.0.1", "SpringBoot");

        return missionEtapeRepository.save(nouvelleEtape);
    }

    @Transactional
    public void supprimerEtape(Long idEtape) {
        missionEtapeRepository.findById(idEtape).ifPresent(etape -> {
            log.warn("Suppression de l'étape {} pour la mission {}", etape.getOrdrePassage(), etape.getOrdreMission().getIdOrdreMission());
            journalService.enregistrerActionSuppression(roleService.getIdUtilisateurConnecte(), "GM_MISSION_ETAPE",
                    "Mission=" + etape.getOrdreMission().getIdOrdreMission() + ",Ordre=" + etape.getOrdrePassage(),
                    "127.0.0.1", "SpringBoot");
            missionEtapeRepository.delete(etape);
        });
    }
}
