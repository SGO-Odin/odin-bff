package com.odin.odinbff.service;

import com.odin.odinbff.model.User;
import com.odin.odinbff.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public final class AuthenticationService implements UserDetailsService {

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    private final UserRepository userRepository;

    public AuthenticationService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateToken(final Authentication authenticate) {
        final var authenticatedUser = (User) authenticate.getPrincipal();

        final Date expiration = new Date(new Date().getTime() + Long.parseLong(this.expiration));

        return Jwts.builder().issuer("Odin")
                .issuedAt(expiration)
                .subject(authenticatedUser.getUsername())
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Boolean isValid(String token) {
        try {
            getUserUUIDFromToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     *
     * @param token
     * @return
     */
    public String getUserUUIDFromToken(String token)  {
        return Jwts.parser().verifyWith(getKey()).
                build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
