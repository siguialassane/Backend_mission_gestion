package com.example.Gestion_mission.service;

import com.example.Gestion_mission.repository.GmAgentRepository;
import com.example.Gestion_mission.security.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Disabled("Diagnostic manuel")
class MissionNotificationServiceTest {

    @Autowired
    private MissionNotificationService missionNotificationService;

    @Autowired
    private GmAgentRepository agentRepository;

    @BeforeEach
    void setupContext() {
        agentRepository.findById(8L).ifPresent(agent -> {
            UserDetailsImpl principal = UserDetailsImpl.build(agent);
            var auth = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        });
    }

    @Test
    void loadNotifications() {
        assertNotNull(missionNotificationService.listerPourUtilisateurCourant());
    }
}
