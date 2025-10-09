package com.example.Gestion_mission.service;

import com.example.Gestion_mission.model.GmAgent;
import com.example.Gestion_mission.model.GmOrdreMission;
import com.example.Gestion_mission.model.GmRemiseJustificatifs;
import com.example.Gestion_mission.repository.GmAgentRepository;
import com.example.Gestion_mission.repository.GmOrdreMissionRepository;
import com.example.Gestion_mission.repository.GmRemiseJustificatifsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class RemiseJustificatifsService {

    private static final Logger log = LoggerFactory.getLogger(RemiseJustificatifsService.class);

    private final GmRemiseJustificatifsRepository remiseRepository;
    private final GmOrdreMissionRepository ordreMissionRepository;
    private final GmAgentRepository agentRepository;
    private final JournalUtilisateurService journalService;
    private final RoleService roleService;

    public RemiseJustificatifsService(GmRemiseJustificatifsRepository remiseRepository,
                                      GmOrdreMissionRepository ordreMissionRepository,
                                      GmAgentRepository agentRepository,
                                      JournalUtilisateurService journalService,
                                      RoleService roleService) {
        this.remiseRepository = remiseRepository;
        this.ordreMissionRepository = ordreMissionRepository;
        this.agentRepository = agentRepository;
        this.journalService = journalService;
        this.roleService = roleService;
    }

    @Transactional
    public GmRemiseJustificatifs enregistrerRemise(Long idOrdreMission, boolean recuRapport, boolean recuFacture, boolean recuOrdre, Long idAgentRecepteur, String observations) {
        GmOrdreMission mission = ordreMissionRepository.findById(idOrdreMission)
                .orElseThrow(() -> new IllegalArgumentException("Mission introuvable: " + idOrdreMission));

        GmRemiseJustificatifs remise = remiseRepository.findByOrdreMissionIdOrdreMission(idOrdreMission)
                .orElseGet(GmRemiseJustificatifs::new);

        remise.setOrdreMission(mission);
        remise.setRecuRapport(recuRapport ? 1 : 0);
        remise.setRecuFacture(recuFacture ? 1 : 0);
        remise.setRecuOrdre(recuOrdre ? 1 : 0);
        remise.setDateRemise(new Date());
        remise.setObservations(observations);

        if (idAgentRecepteur != null) {
            GmAgent agent = agentRepository.findById(idAgentRecepteur)
                    .orElseThrow(() -> new IllegalArgumentException("Agent introuvable"));
            remise.setAgentRecepteur(agent);
        }

        log.info("Mise à jour des justificatifs pour la mission {}", idOrdreMission);
        journalService.enregistrerActionModification(roleService.getIdUtilisateurConnecte(), "GM_REMISE_JUSTIFICATIFS",
                "Ancienne remise", "Mission=" + idOrdreMission + ",Rapport=" + recuRapport + ",Facture=" + recuFacture + ",Ordre=" + recuOrdre,
                "127.0.0.1", "SpringBoot");

        return remiseRepository.save(remise);
    }

    @Transactional(readOnly = true)
    public GmRemiseJustificatifs consulterRemise(Long idOrdreMission) {
        log.debug("Consultation des justificatifs pour la mission {}", idOrdreMission);
        return remiseRepository.findByOrdreMissionIdOrdreMission(idOrdreMission)
                .orElse(null);
    }
}
