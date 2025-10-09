package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.GmMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GmMenuRepository extends JpaRepository<GmMenu, Long> {

    Optional<GmMenu> findByCodeMenu(String codeMenu);
}
