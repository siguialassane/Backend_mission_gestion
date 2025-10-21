package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.dto.MissionDetailViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class MissionDetailViewRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<MissionDetailViewDTO> rowMapper = new RowMapper<MissionDetailViewDTO>() {
        @Override
        public MissionDetailViewDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            MissionDetailViewDTO dto = new MissionDetailViewDTO();
            dto.setIdOrdreMission(rs.getLong("ID_ORDRE_MISSION"));
            dto.setCodeMission(rs.getString("CODE_MISSION"));
            dto.setObjetMission(rs.getString("OBJET_MISSION"));
            dto.setDateDebutMission(rs.getTimestamp("DATE_DEBUT_MISSION"));
            dto.setDateFinMission(rs.getTimestamp("DATE_FIN_MISSION"));
            dto.setLieuDepart(rs.getString("LIEU_DEPART"));
            dto.setLieuDestination(rs.getString("LIEU_DESTINATION"));
            dto.setMotifMission(rs.getString("MOTIF_MISSION"));
            dto.setStatutMission(rs.getString("STATUT_MISSION"));
            dto.setEntiteCode(rs.getString("ENTITE_CODE"));
            dto.setEntiteLibelle(rs.getString("ENTITE_LIBELLE"));
            dto.setNomChauffeur(rs.getString("NOM_CHAUFFEUR"));
            dto.setInfoVehicule(rs.getString("INFO_VEHICULE"));
            dto.setNomAdjudant(rs.getString("NOM_ADJUDANT"));
            dto.setDestinataire(rs.getString("DESTINATAIRE"));
            dto.setDateMiseAJour(rs.getTimestamp("DATE_MISE_AJOUR"));
            dto.setDateCreation(rs.getTimestamp("DATE_CREATION"));
            dto.setChefMissionNom(rs.getString("CHEF_MISSION_NOM"));
            dto.setChefMissionPrenom(rs.getString("CHEF_MISSION_PRENOM"));
            dto.setNombreAgents(rs.getLong("NOMBRE_AGENTS"));
            dto.setItineraireVilles(rs.getString("ITINERAIRE_VILLES"));
            return dto;
        }
    };

    public Optional<MissionDetailViewDTO> findMissionDetailByMissionId(Long missionId) {
        String sql = "SELECT * FROM V_GM_MISSION_DETAIL WHERE ID_ORDRE_MISSION = ?";
        try {
            MissionDetailViewDTO result = jdbcTemplate.queryForObject(sql, new Object[]{missionId}, rowMapper);
            return Optional.ofNullable(result);
        } catch (Exception e) {
            System.err.println("[MISSION-VIEW] Erreur récupération mission " + missionId + ": " + e.getMessage());
            return Optional.empty();
        }
    }
}
