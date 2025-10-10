package com.example.Gestion_mission.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SessionDebugFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(SessionDebugFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String uri = request.getRequestURI();
        HttpSession session = request.getSession(false);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        logger.info("🔍 REQUEST {} {} | Session: {} | Auth: {}", 
            request.getMethod(), 
            uri,
            session != null ? session.getId() : "NONE",
            auth != null && auth.isAuthenticated() ? auth.getName() : "NONE"
        );
        
        if (session != null) {
            logger.info("   └─ Session attributes: {}", session.getAttributeNames().hasMoreElements() ? "EXISTS" : "EMPTY");
        }
        
        filterChain.doFilter(request, response);
    }
}
