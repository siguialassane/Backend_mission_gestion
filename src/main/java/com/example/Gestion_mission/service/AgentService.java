package com.example.Gestion_mission.service;

import com.example.Gestion_mission.dto.AgentCreationRequest;
import com.example.Gestion_mission.model.GmAgent;
import com.example.Gestion_mission.model.GmRole;
import com.example.Gestion_mission.repository.GmAgentRepository;
import com.example.Gestion_mission.repository.GmRoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AgentService {

    private final GmAgentRepository agentRepository;
    private final GmRoleRepository roleRepository;
    private final RoleService roleService;

    public AgentService(GmAgentRepository agentRepository, GmRoleRepository roleRepository, RoleService roleService) {
        this.agentRepository = agentRepository;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
    }

    public Page<GmAgent> rechercher(String query, int page, int size) {
        if (query == null || query.isBlank()) {
            return Page.empty();
        }
        Pageable pageable = PageRequest.of(Math.max(page, 0), Math.min(Math.max(size, 1), 50), Sort.by("nomAgent"));
        return agentRepository.searchAgents(query.trim(), pageable);
    }

    public GmAgent creerAgent(AgentCreationRequest request) {
        String roleCible = (request.getRole() == null || request.getRole().isBlank()) ? "GESTIONNAIRE" : request.getRole().trim().toUpperCase();
        GmRole role = roleRepository.findByNomRole(roleCible)
                .orElseThrow(() -> new IllegalArgumentException("Rôle introuvable : " + roleCible));

        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            // Vérifier si email existe ET n'est pas supprimé (DELETED = 0)
            Boolean existe = agentRepository.findByEmailAgentAndDeleted(request.getEmail().trim()).isPresent();
            if (Boolean.TRUE.equals(existe)) {
                throw new IllegalArgumentException("Email déjà utilisé : " + request.getEmail().trim() + ". Veuillez utiliser un autre email.");
            }
        }

        GmAgent agent = new GmAgent();
        agent.setMatriculeAgent(request.getMatricule());
        agent.setNomAgent(request.getNom());
        agent.setPrenomAgent(request.getPrenom());
        agent.setEmailAgent(request.getEmail());
        agent.setTelephoneAgent(request.getTelephone());
        agent.setFonctionAgent(request.getFonction());
        agent.setStatutActifAgent(1);
        Date now = new Date();
        agent.setDateCreationAgent(now);
        agent.setCreatedAt(now);
        Long utilisateurId = roleService.getIdUtilisateurConnecte();
        // Set both CREATED_BY (audit) and ID_UTILISATEUR_CREATEUR (FK for filtering)
        if (utilisateurId != null) {
            agent.setCreatedBy(String.valueOf(utilisateurId));
            agent.setIdUtilisateurCreateur(utilisateurId);
        } else {
            // Fallback - should rarely happen if user is authenticated
            agent.setCreatedBy("SYSTEM");
        }
        agent.getRoles().add(role);
        return agentRepository.save(agent);
    }

    public List<GmAgent> listerAgentsCreesParUtilisateur(Long idUtilisateur) {
        if (idUtilisateur == null) {
            return List.of();
        }
        // Cherche les agents créés par cet utilisateur via la FK ID_UTILISATEUR_CREATEUR
        return agentRepository.findByIdUtilisateurCreateur(idUtilisateur);
    }

    public GmAgent modifierAgent(Long idAgent, AgentCreationRequest request) {
        GmAgent agent = agentRepository.findById(idAgent)
                .orElseThrow(() -> new IllegalArgumentException("Agent non trouvé : " + idAgent));

        // Vérifier que l'utilisateur connecté a créé cet agent
        Long utilisateurId = roleService.getIdUtilisateurConnecte();
        if (!agent.getIdUtilisateurCreateur().equals(utilisateurId)) {
            throw new IllegalArgumentException("Vous n'avez pas la permission de modifier cet agent");
        }

        // Vérifier si email déjà utilisé (par un autre agent actif)
        if (request.getEmail() != null && !request.getEmail().isBlank() 
            && !request.getEmail().equals(agent.getEmailAgent())) {
            // Vérifier si email existe ET n'est pas supprimé (DELETED = 0)
            Boolean existe = agentRepository.findByEmailAgentAndDeleted(request.getEmail().trim()).isPresent();
            if (Boolean.TRUE.equals(existe)) {
                throw new IllegalArgumentException("Email déjà utilisé : " + request.getEmail().trim() + ". Veuillez utiliser un autre email.");
            }
        }

        agent.setMatriculeAgent(request.getMatricule());
        agent.setNomAgent(request.getNom());
        agent.setPrenomAgent(request.getPrenom());
        agent.setEmailAgent(request.getEmail());
        agent.setTelephoneAgent(request.getTelephone());
        agent.setFonctionAgent(request.getFonction());
        agent.setUpdatedAt(new Date());
        agent.setUpdatedBy(String.valueOf(utilisateurId));

        return agentRepository.save(agent);
    }

    public void supprimerAgent(Long idAgent) {
        GmAgent agent = agentRepository.findById(idAgent)
                .orElseThrow(() -> new IllegalArgumentException("Agent non trouvé : " + idAgent));

        // Vérifier que l'utilisateur connecté a créé cet agent
        Long utilisateurId = roleService.getIdUtilisateurConnecte();
        if (!agent.getIdUtilisateurCreateur().equals(utilisateurId)) {
            throw new IllegalArgumentException("Vous n'avez pas la permission de supprimer cet agent");
        }

        // Soft delete
        agent.setDeleted(1);
        agent.setDeletedAt(new Date());
        agent.setUpdatedBy(String.valueOf(utilisateurId));
        agent.setUpdatedAt(new Date());

        agentRepository.save(agent);
    }

    public GmAgent lireAgent(Long idAgent) {
        GmAgent agent = agentRepository.findById(idAgent)
                .orElseThrow(() -> new IllegalArgumentException("Agent non trouvé : " + idAgent));

        // Vérifier que l'utilisateur connecté a créé cet agent
        Long utilisateurId = roleService.getIdUtilisateurConnecte();
        if (!agent.getIdUtilisateurCreateur().equals(utilisateurId)) {
            throw new IllegalArgumentException("Vous n'avez pas la permission de voir cet agent");
        }

        return agent;
    }
}
