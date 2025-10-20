package com.example.Gestion_mission.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("utf8EncodingFilter")
public class CharacterEncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // Set UTF-8 encoding for response
        response.setCharacterEncoding("UTF-8");
        httpResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpResponse.setContentType("application/json;charset=UTF-8");
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
