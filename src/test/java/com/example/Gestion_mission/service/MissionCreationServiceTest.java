package com.example.Gestion_mission.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@Rollback
@Disabled("Scenario de test manuel désactivé – utilisé uniquement pour diagnostic local")
class MissionCreationServiceTest {

    @Autowired
    private GmOrdreMissionService ordreMissionService;

    @Test
    void placeholder() {
        assertNotNull(ordreMissionService);
    }
}
