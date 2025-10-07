package com.example.Gestion_mission.aspect;

import com.example.Gestion_mission.annotation.RoleAutorise;
import com.example.Gestion_mission.security.UserDetailsImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
public class RoleAutoriseAspect {

    @Around("@annotation(roleAutoriseAnnotation)")
    public Object verifierRoleAutorise(ProceedingJoinPoint joinPoint, RoleAutorise roleAutoriseAnnotation) throws Throwable {
        // Récupérer l'authentification courante
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new SecurityException("Non authentifié");
        }
        
        if (authentication.getPrincipal() instanceof UserDetailsImpl) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            
            // Récupérer les rôles de l'utilisateur
            List<String> rolesUtilisateur = userDetails.getAuthorities().stream()
                    .map(auth -> auth.getAuthority())
                    .collect(Collectors.toList());
            
            // Vérifier si l'utilisateur a les rôles requis
            boolean roleAutorise = false;
            for (String roleRequis : roleAutoriseAnnotation.roles()) {
                if (rolesUtilisateur.contains(roleRequis)) {
                    roleAutorise = true;
                    break;
                }
            }
            
            if (!roleAutorise) {
                throw new SecurityException("Accès refusé : rôle insuffisant");
            }
            
            // Si la méthode est une suppression et que l'utilisateur n'est pas ADMIN
            if (!roleAutoriseAnnotation.peutSupprimer() && 
                (joinPoint.getSignature().getName().contains("delete") || 
                 joinPoint.getSignature().getName().contains("remove"))) {
                
                boolean isAdmin = rolesUtilisateur.contains("ADMIN");
                if (!isAdmin) {
                    throw new SecurityException("Accès refusé : les gestionnaires ne peuvent pas supprimer");
                }
            }
            
            // Si la méthode est une modification et que l'utilisateur n'est pas ADMIN
            if (!roleAutoriseAnnotation.peutModifier() && 
                (joinPoint.getSignature().getName().contains("update") || 
                 joinPoint.getSignature().getName().contains("modify") ||
                 joinPoint.getSignature().getName().contains("save"))) {
                
                boolean isAdmin = rolesUtilisateur.contains("ADMIN");
                if (!isAdmin) {
                    throw new SecurityException("Accès refusé : les gestionnaires ne peuvent pas modifier");
                }
            }
        } else {
            throw new SecurityException("Non authentifié");
        }
        
        // Autorisation accordée, exécuter la méthode originale
        return joinPoint.proceed();
    }
}