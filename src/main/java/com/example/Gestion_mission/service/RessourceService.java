package com.example.Gestion_mission.service;

import com.example.Gestion_mission.model.GmRessource;
import com.example.Gestion_mission.repository.GmRessourceRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RessourceService {

    private final GmRessourceRepository ressourceRepository;

    public RessourceService(GmRessourceRepository ressourceRepository) {
        this.ressourceRepository = ressourceRepository;
    }

    public List<GmRessource> listerDisponibles() {
        return ressourceRepository.findAll(Sort.by("libelleRessource").ascending());
    }
}
