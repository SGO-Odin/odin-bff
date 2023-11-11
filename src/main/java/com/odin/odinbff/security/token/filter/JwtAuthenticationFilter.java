package com.odin.odinbff.security.token.filter;

import com.odin.odinbff.security.service.UserService;
import com.odin.odinbff.security.token.JwtManager;
import com.odin.odinbff.security.token.JwtUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtUserDetailsService userService;

    private JwtManager jwtManager;

    public JwtAuthenticationFilter(JwtUserDetailsService userService, JwtManager jwtManager) {
        this.userService = userService;
        this.jwtManager = jwtManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var token = this.retrieveToken(request);
        if (token != null && this.jwtManager.isValid(token)) {
            UserDetails user = this.userService.loadUserByUsername(this.jwtManager.getUsername(token));
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(
                            user, null, user.getAuthorities()));
        }

        filterChain.doFilter(request, response);
    }

    private String retrieveToken(HttpServletRequest request) {
        var hAuth = request.getHeader("Authorization");
        if(hAuth == null || hAuth.isEmpty() || !hAuth.startsWith("Bearer ")) {
            return null;
        }
        return hAuth.substring(7, hAuth.length());
    }

}
