package com.example.Gestion_mission.service;

import com.example.Gestion_mission.model.GmAgent;
import com.example.Gestion_mission.model.GmMissionAgent;
import com.example.Gestion_mission.model.GmOrdreMission;
import com.example.Gestion_mission.repository.GmAgentRepository;
import com.example.Gestion_mission.repository.GmMissionAgentRepository;
import com.example.Gestion_mission.repository.GmOrdreMissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MissionAgentService {

    private static final Logger log = LoggerFactory.getLogger(MissionAgentService.class);

    private final GmMissionAgentRepository missionAgentRepository;
    private final GmOrdreMissionRepository ordreMissionRepository;
    private final GmAgentRepository agentRepository;
    private final JournalUtilisateurService journalService;
    private final RoleService roleService;

    public MissionAgentService(GmMissionAgentRepository missionAgentRepository,
                               GmOrdreMissionRepository ordreMissionRepository,
                               GmAgentRepository agentRepository,
                               JournalUtilisateurService journalService,
                               RoleService roleService) {
        this.missionAgentRepository = missionAgentRepository;
        this.ordreMissionRepository = ordreMissionRepository;
        this.agentRepository = agentRepository;
        this.journalService = journalService;
        this.roleService = roleService;
    }

    @Transactional(readOnly = true)
    public List<GmMissionAgent> listerParticipants(Long missionId) {
        journalService.enregistrerActionConsultation(roleService.getIdUtilisateurConnecte(),
                "GM_MISSION_AGENT", "127.0.0.1", "SpringBoot");
        return missionAgentRepository.findByOrdreMissionIdOrdreMission(missionId);
    }

    @Transactional
    public GmMissionAgent ajouterParticipant(Long missionId, Long agentId, String roleMission) {
        if (missionAgentRepository.existsAssociation(missionId, agentId) > 0) {
            throw new IllegalArgumentException("Cet agent est déjà associé à la mission");
        }

        GmOrdreMission mission = ordreMissionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("Mission introuvable: " + missionId));
        GmAgent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new IllegalArgumentException("Agent introuvable: " + agentId));

        GmMissionAgent missionAgent = new GmMissionAgent();
        missionAgent.setOrdreMission(mission);
        missionAgent.setAgent(agent);
        missionAgent.setRoleMission(roleMission);
        missionAgent.setCreatedAt(new Date());
        missionAgent.setCreatedBy(roleService.getRoleUtilisateur());

        GmMissionAgent saved = missionAgentRepository.save(missionAgent);

        log.info("Ajout de l'agent {} à la mission {} avec le rôle {}", agentId, missionId, roleMission);
        journalService.enregistrerActionCreation(roleService.getIdUtilisateurConnecte(),
                "GM_MISSION_AGENT",
                "Mission=" + missionId + ",Agent=" + agentId + ",Rôle=" + roleMission,
                "127.0.0.1", "SpringBoot");

        return saved;
    }

    @Transactional
    public void supprimerParticipant(Long missionAgentId) {
        missionAgentRepository.findById(missionAgentId).ifPresent(participant -> {
            log.warn("Suppression du participant {} de la mission {}", participant.getAgent().getIdAgent(),
                    participant.getOrdreMission().getIdOrdreMission());
            journalService.enregistrerActionSuppression(roleService.getIdUtilisateurConnecte(),
                    "GM_MISSION_AGENT",
                    "Mission=" + participant.getOrdreMission().getIdOrdreMission() + ",Agent=" + participant.getAgent().getIdAgent(),
                    "127.0.0.1", "SpringBoot");
            missionAgentRepository.delete(participant);
        });
    }
}