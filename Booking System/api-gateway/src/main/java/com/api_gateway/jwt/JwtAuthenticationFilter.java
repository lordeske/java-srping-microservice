package com.api_gateway.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("[FILTER] Authorization header nije prisutan ili ne počinje sa Bearer");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        System.out.println("[FILTER] Extracted token: " + token);

        String email = jwtService.extractEmail(token);
        String role = jwtService.extractRole(token);
        System.out.println("[FILTER] ROLE u tokenu: " + role);



        if (!jwtService.isTokenValid(token)) {
            System.out.println("[FILTER] Token NIJE validan ili je istekao!");
            filterChain.doFilter(request, response);
            return;
        }


        System.out.println("[FILTER] Token valid. Email: " + email + ", Role: " + role);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                email,
                null,
                List.of(new SimpleGrantedAuthority(role))
        );

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
        System.out.println("[FILTER] SecurityContext postavljen za korisnika: " + email);

        filterChain.doFilter(request, response);
    }

}
