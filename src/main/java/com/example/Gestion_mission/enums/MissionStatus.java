package com.example.Gestion_mission.enums;

public final class MissionStatus {

    private MissionStatus() {
        // Utility class
    }

    public static final String BROUILLON = "BROUILLON";
    public static final String EN_ATTENTE_VALIDATION_RH = "EN_ATTENTE_VALIDATION_RH";
    public static final String REFUSEE_RH = "REFUSEE_RH";
    public static final String EN_ATTENTE_BUDGET_MG = "EN_ATTENTE_BUDGET_MG";
    public static final String REFUSEE_MG = "REFUSEE_MG";
    public static final String EN_ATTENTE_CAISSE = "EN_ATTENTE_CAISSE";
    public static final String REFUSEE_CAISSE = "REFUSEE_CAISSE";
    public static final String FONDS_CONFIRMES = "FONDS_CONFIRMES";
    public static final String PRET_IMPRESSION = "PRET_IMPRESSION";
    public static final String TERMINEE = "TERMINEE";
    public static final String ANNULEE = "ANNULEE";
}
