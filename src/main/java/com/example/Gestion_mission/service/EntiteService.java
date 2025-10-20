package com.example.Gestion_mission.service;

import com.example.Gestion_mission.model.AcEntite;
import com.example.Gestion_mission.repository.AcEntiteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EntiteService {

    private final AcEntiteRepository entiteRepository;

    public EntiteService(AcEntiteRepository entiteRepository) {
        this.entiteRepository = entiteRepository;
    }

    public Page<AcEntite> rechercher(String query, int page, int size) {
        int pageIndex = Math.max(page, 0);
        int pageSize = Math.min(Math.max(size, 1), 100);

        List<AcEntite> source;
        if (query == null || query.isBlank()) {
            source = entiteRepository.findAll(Sort.by("libelle"));
        } else {
            String like = query.trim().toLowerCase();
            source = entiteRepository.findAll().stream()
                    .filter(entite -> {
                        String libelle = entite.getLibelle() != null ? entite.getLibelle().toLowerCase() : "";
                        String libelleLong = entite.getLibelleLong() != null ? entite.getLibelleLong().toLowerCase() : "";
                        return libelle.contains(like) || libelleLong.contains(like);
                    })
                    .sorted((a, b) -> {
                        String libA = a.getLibelle() != null ? a.getLibelle() : "";
                        String libB = b.getLibelle() != null ? b.getLibelle() : "";
                        return libA.compareToIgnoreCase(libB);
                    })
                    .toList();
        }

        if (source.isEmpty()) {
            return new PageImpl<>(Collections.emptyList(), PageRequest.of(pageIndex, pageSize, Sort.by("libelle")), 0);
        }

        int fromIndex = Math.min(pageIndex * pageSize, source.size());
        int toIndex = Math.min(fromIndex + pageSize, source.size());
        List<AcEntite> content = source.subList(fromIndex, toIndex);

        return new PageImpl<>(content, PageRequest.of(pageIndex, pageSize, Sort.by("libelle")), source.size());
    }
}
