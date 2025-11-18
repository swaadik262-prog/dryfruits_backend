package com.dryfruits.dryfruitsbackend.filter;

import com.dryfruits.dryfruitsbackend.exception.EmptyValueExistException;
import com.dryfruits.dryfruitsbackend.model.CustomUserDetails;
import com.dryfruits.dryfruitsbackend.service.CustomUserDetailsService;
import com.dryfruits.dryfruitsbackend.service.TokenBlacklistService;
import com.dryfruits.dryfruitsbackend.util.JwtUtil;
import com.dryfruits.dryfruitsbackend.util.Util;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    private CustomUserDetailsService customUserDetailsService;
    private TokenBlacklistService tokenBlacklistService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(Util.AUTHORIZATION);
        String token = null;
        String username = null;

        if (header != null && header.startsWith(Util.BEARER)){
            token = header.substring(7);
            username = jwtUtil.getUsername(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null && !tokenBlacklistService.isTokenBlacklisted(token)){
            CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(token, username)){
                UsernamePasswordAuthenticationToken authenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}