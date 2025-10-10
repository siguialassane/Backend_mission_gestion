package com.example.Gestion_mission.service;

import com.example.Gestion_mission.model.GmAgent;
import com.example.Gestion_mission.repository.GmAgentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    private final GmAgentRepository agentRepository;

    public AgentService(GmAgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public Page<GmAgent> rechercher(String query, int page, int size) {
        if (query == null || query.isBlank()) {
            return Page.empty();
        }
        Pageable pageable = PageRequest.of(Math.max(page, 0), Math.min(Math.max(size, 1), 50), Sort.by("nomAgent"));
        return agentRepository.searchAgents(query.trim(), pageable);
    }
}
