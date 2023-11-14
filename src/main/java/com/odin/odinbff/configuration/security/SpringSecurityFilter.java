package com.odin.odinbff.configuration.security;

import com.odin.odinbff.service.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SpringSecurityFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationService authService;

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {
        final var jwtToke = getHeaderToken(request);

        if (authService.isValid(jwtToke)) {
            UserDetails user = authService.loadUserByUsername(authService.getUserUUIDFromToken(jwtToke));
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(
                            user, null, user.getAuthorities()));
        }

        filterChain.doFilter(request, response);
    }

    private String getHeaderToken(final HttpServletRequest request) {
        var hAuth = request.getHeader("Authorization");
        if(hAuth == null || !hAuth.startsWith("Bearer ")) {
            return null;
        }
        return hAuth.substring(7);
    }
}
