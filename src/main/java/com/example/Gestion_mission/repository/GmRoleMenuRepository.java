package com.example.Gestion_mission.repository;

import com.example.Gestion_mission.model.GmRoleMenu;
import com.example.Gestion_mission.model.GmRoleMenuId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GmRoleMenuRepository extends JpaRepository<GmRoleMenu, GmRoleMenuId> {

    List<GmRoleMenu> findByRoleIdRole(Long idRole);

    List<GmRoleMenu> findByMenuIdMenu(Long idMenu);
}
