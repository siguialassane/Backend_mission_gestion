package com.example.Gestion_mission.service;

import com.example.Gestion_mission.model.AcVille;
import com.example.Gestion_mission.repository.AcVilleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class VilleService {

    private final AcVilleRepository villeRepository;

    public VilleService(AcVilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    public Page<AcVille> rechercher(String query, int page, int size) {
        Pageable pageable = PageRequest.of(Math.max(page, 0), Math.min(Math.max(size, 1), 100), Sort.by("libelle"));
        if (query == null || query.isBlank()) {
            return villeRepository.findAll(pageable);
        }
        String like = query.trim();
        return villeRepository.findByLibelleContainingIgnoreCaseOrLibelleLongContainingIgnoreCase(like, like, pageable);
    }
}
