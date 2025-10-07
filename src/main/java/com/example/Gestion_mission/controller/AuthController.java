package com.example.Gestion_mission.controller;

import com.example.Gestion_mission.model.GmAgent;
import com.example.Gestion_mission.model.GmRole;
import com.example.Gestion_mission.payload.JwtResponse;
import com.example.Gestion_mission.payload.LoginRequest;
import com.example.Gestion_mission.payload.MessageResponse;
import com.example.Gestion_mission.payload.SignupRequest;
import com.example.Gestion_mission.repository.GmAgentRepository;
import com.example.Gestion_mission.repository.GmRoleRepository;
import com.example.Gestion_mission.security.JwtUtils;
import com.example.Gestion_mission.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    GmAgentRepository gmAgentRepository;

    @Autowired
    GmRoleRepository gmRoleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (gmAgentRepository.existsByEmailAgent(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        GmAgent agent = new GmAgent();
        agent.setNomAgent(signUpRequest.getNom());
        agent.setPrenomAgent(signUpRequest.getPrenom());
        agent.setMatriculeAgent(signUpRequest.getMatricule());
        agent.setEmailAgent(signUpRequest.getEmail());
        agent.setMotDePasse(encoder.encode(signUpRequest.getPassword()));
        agent.setStatutActifAgent(1); // Default to active
        agent.setDateCreationAgent(new Date()); // Set creation date

        Set<String> strRoles = signUpRequest.getRole();
        Set<GmRole> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            GmRole gestionnaireRole = gmRoleRepository.findByNomRole("GESTIONNAIRE")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(gestionnaireRole);
        } else {
            strRoles.forEach(role -> {
                switch (role.toLowerCase()) {
                    case "admin":
                        GmRole adminRole = gmRoleRepository.findByNomRole("ADMIN")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "gestionnaire":
                        GmRole gestionnaireRole = gmRoleRepository.findByNomRole("GESTIONNAIRE")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(gestionnaireRole);
                        break;
                    default:
                        GmRole defaultRole = gmRoleRepository.findByNomRole("GESTIONNAIRE")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(defaultRole);
                }
            });
        }

        agent.setRoles(roles);
        gmAgentRepository.save(agent);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}