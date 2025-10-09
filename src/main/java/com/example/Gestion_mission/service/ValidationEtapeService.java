package com.example.Gestion_mission.service;

import com.example.Gestion_mission.model.GmValidationEtape;
import com.example.Gestion_mission.repository.GmValidationEtapeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ValidationEtapeService {

    private static final Logger log = LoggerFactory.getLogger(ValidationEtapeService.class);

    private final GmValidationEtapeRepository validationEtapeRepository;

    public ValidationEtapeService(GmValidationEtapeRepository validationEtapeRepository) {
        this.validationEtapeRepository = validationEtapeRepository;
    }

    @Transactional(readOnly = true)
    public List<GmValidationEtape> listerParOrdre() {
        log.debug("Récupération des étapes de validation triées par ordre");
        return validationEtapeRepository.findAllByOrderByOrdreEtapeAsc();
    }
}
