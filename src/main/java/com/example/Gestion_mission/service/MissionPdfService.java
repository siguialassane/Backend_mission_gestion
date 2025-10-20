package com.example.Gestion_mission.service;

import com.example.Gestion_mission.model.GmOrdreMission;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class MissionPdfService {

    private final Path storageDirectory;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRANCE);

    public MissionPdfService(@Value("${missions.pdf.output-dir:storage/pdfs}") String directory) {
        try {
            this.storageDirectory = Paths.get(directory).toAbsolutePath().normalize();
            Files.createDirectories(this.storageDirectory);
        } catch (IOException ex) {
            throw new IllegalStateException("Impossible d'initialiser le répertoire PDF", ex);
        }
    }

    public String generateDocument(GmOrdreMission mission, DocumentType type) {
        if (mission == null || mission.getIdOrdreMission() == null) {
            throw new IllegalArgumentException("Mission invalide pour la génération PDF");
        }

        String fileName = buildFileName(mission, type);
        Path target = storageDirectory.resolve(fileName);

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream content = new PDPageContentStream(document, page)) {
                content.beginText();
                content.setFont(PDType1Font.HELVETICA_BOLD, 18);
                content.newLineAtOffset(60, 780);
                content.showText(type.getTitre());

                content.setFont(PDType1Font.HELVETICA, 12);
                float y = 750;
                y = writeLine(content, y, "Code mission : " + safe(mission.getCodeMission()));
                y = writeLine(content, y, "Objet : " + safe(mission.getObjetMission()));
                y = writeLine(content, y, "Entité : " + safe(mission.getEntiteCode()));
                y = writeLine(content, y, "Période : " + formatDateRange(mission));
                y = writeLine(content, y, "Trajet : " + safe(mission.getLieuDepart()) + " → " + safe(mission.getLieuDestination()));
                y = writeLine(content, y, "Statut actuel : " + safe(mission.getStatutMission()));
                if (mission.getMotifMission() != null && !mission.getMotifMission().isBlank()) {
                    y = writeLine(content, y, "Motif : " + safe(mission.getMotifMission()));
                }
                if (mission.getFraisEstime() != null) {
                    y = writeLine(content, y, String.format(Locale.FRANCE, "Frais estimés : %.0f FCFA", mission.getFraisEstime()));
                }

                y = writeLine(content, y, "");
                y = writeLine(content, y, "Informations signature :");
                switch (type) {
                    case CHEF_DEMANDE -> {
                        y = writeLine(content, y, "Chef de service : mission soumise le " + formatDate(mission.getDateCreation()));
                    }
                    case RH_VALIDATION -> {
                        y = writeLine(content, y, "Validation RH : " + formatDate(mission.getDateValidationRh()));
                    }
                    case FINAL_ORDER -> {
                        y = writeLine(content, y, "Validation MG : " + formatDate(mission.getDateValidationMg()));
                        y = writeLine(content, y, "Validation caisse : " + formatDate(mission.getDateValidationCaisse()));
                        y = writeLine(content, y, "Clôture RH : " + formatDate(mission.getDateMiseAJour()));
                    }
                }

                content.endText();
            }

            document.save(target.toFile());
        } catch (IOException ex) {
            throw new IllegalStateException("Erreur lors de la génération du PDF de mission", ex);
        }

        return fileName;
    }

    public Resource loadAsResource(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            return null;
        }
        try {
            Path file = storageDirectory.resolve(fileName).normalize();
            if (!Files.exists(file)) {
                return null;
            }
            return new UrlResource(file.toUri());
        } catch (IOException ex) {
            throw new IllegalStateException("Impossible de charger le PDF demandé", ex);
        }
    }

    public boolean deleteIfExists(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            return false;
        }
        try {
            return Files.deleteIfExists(storageDirectory.resolve(fileName));
        } catch (IOException ex) {
            return false;
        }
    }

    private static float writeLine(PDPageContentStream content, float currentY, String text) throws IOException {
        currentY -= 18;
        content.newLineAtOffset(0, -18);
        content.showText(text == null ? "" : text);
        return currentY;
    }

    private static String safe(String value) {
        return value == null ? "—" : value;
    }

    private static String formatDateRange(GmOrdreMission mission) {
        if (mission.getDateDebutMission() == null || mission.getDateFinMission() == null) {
            return "Non renseigné";
        }
        return formatDate(mission.getDateDebutMission()) + " → " + formatDate(mission.getDateFinMission());
    }

    private static String formatDate(java.util.Date value) {
        if (value == null) {
            return "—";
        }
        return DATE_FORMAT.format(value.toInstant().atZone(ZoneId.systemDefault()));
    }

    private static String buildFileName(GmOrdreMission mission, DocumentType type) {
        String code = mission.getCodeMission() != null ? mission.getCodeMission().replaceAll("[^A-Za-z0-9_-]", "-") : "MISSION";
        return code + "-" + type.getFileSuffix() + "-" + mission.getIdOrdreMission() + ".pdf";
    }

    public enum DocumentType {
        CHEF_DEMANDE("chef", "Demande d'ordre de mission"),
        RH_VALIDATION("rh", "Validation RH de l'ordre de mission"),
        FINAL_ORDER("final", "Ordre de mission définitif");

        private final String fileSuffix;
        private final String titre;

        DocumentType(String fileSuffix, String titre) {
            this.fileSuffix = fileSuffix;
            this.titre = titre;
        }

        public String getFileSuffix() {
            return fileSuffix;
        }

        public String getTitre() {
            return titre;
        }

        public static DocumentType fromCode(String value) {
            for (DocumentType type : values()) {
                if (type.name().equalsIgnoreCase(value) || type.fileSuffix.equalsIgnoreCase(value)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Type de document inconnu : " + value);
        }
    }
}
