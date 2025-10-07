package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.GmAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GmAgentRepository extends JpaRepository<GmAgent, Long> {

    @Query("SELECT a FROM GmAgent a JOIN FETCH a.roles WHERE a.emailAgent = :email")
    Optional<GmAgent> findByEmailAgent(@Param("email") String emailAgent);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM GmAgent u WHERE u.emailAgent = ?1")
    Boolean existsByEmailAgent(String email);

}
