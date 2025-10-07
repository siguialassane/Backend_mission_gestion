package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.GmRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GmRoleRepository extends JpaRepository<GmRole, Long> {

    Optional<GmRole> findByNomRole(String nomRole);

}
