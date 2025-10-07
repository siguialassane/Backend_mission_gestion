package com.example.Gestion_mission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableJpaRepositories(basePackages = "com.example.Gestion_mission.repository")
@EnableTransactionManagement
public class GestionMissionApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionMissionApplication.class, args);
	}

}
