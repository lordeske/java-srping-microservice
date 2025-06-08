package com.auth_service.service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private JwtService jwtService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String useEmail;

        if(authHeader == null || !authHeader.startsWith("Bearer ") )
        {
            filterChain.doFilter(request, response);
            return;
        }


        jwt = authHeader.substring(7);

        try {

            useEmail = jwtService.extractEmail(jwt);
        }
        catch (Exception e)
        {
            filterChain.doFilter(request, response);
            return;
        }


        if(useEmail != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(useEmail);


            if(jwtService.isTokenValid(jwt,userDetails))
            {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());


                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);

            }


        }

        filterChain.doFilter(request, response);

    }
}
