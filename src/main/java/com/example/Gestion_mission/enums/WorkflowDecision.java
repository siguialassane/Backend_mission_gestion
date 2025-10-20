package com.example.Gestion_mission.enums;

public enum WorkflowDecision {
    VALIDEE,
    APPROUVEE,
    REJETEE,
    ANNULEE;

    public static String normaliser(String input) {
        return resolve(input).name();
    }

    public boolean matches(String value) {
        if (value == null) {
            return false;
        }
        return this.name().equalsIgnoreCase(value.trim());
    }

    public static WorkflowDecision resolve(String input) {
        if (input == null) {
            return VALIDEE;
        }
        String upper = input.trim().toUpperCase();
        if (upper.isBlank()) {
            return VALIDEE;
        }
        switch (upper) {
            case "VALIDEE":
            case "VALIDÉE":
            case "APPROUVEE":
            case "APPROUVÉE":
            case "ACCEPTEE":
            case "ACCEPTÉE":
                return VALIDEE;
            case "REJETEE":
            case "REJETÉE":
            case "REFUSEE":
            case "REFUSÉE":
                return REJETEE;
            case "ANNULEE":
            case "ANNULÉE":
                return ANNULEE;
            default:
                return VALIDEE;
        }
    }
}
