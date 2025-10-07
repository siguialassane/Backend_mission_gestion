package com.example.Gestion_mission.service;

import com.example.Gestion_mission.model.GmAgent;
import com.example.Gestion_mission.repository.GmAgentRepository;
import com.example.Gestion_mission.security.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private GmAgentRepository gmAgentRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Chargement de l'utilisateur avec l'email: {}", username);
        GmAgent agent = gmAgentRepository.findByEmailAgent(username)
                .orElseThrow(() -> {
                    logger.warn("Utilisateur introuvable pour l'email: {}", username);
                    return new UsernameNotFoundException("User Not Found with username: " + username);
                });

        logger.debug("Utilisateur trouvé, ID: {}", agent.getIdAgent());

        return UserDetailsImpl.build(agent);
    }
}