package com.songs.wallah.security;

import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.songs.wallah.security.SpingConstraints.SecurityConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final SecretKey key;

    public JwtUtil(@Value("${jwt.secret}") String jwtSecret) {
        // Use ONE single source of truth for the secret
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(String username) {

        Instant now = Instant.now();

        return Jwts.builder()
                .subject(username)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(SecurityConstants.EXPIRATION_TIME)))
                .signWith(key)
                .compact();
    }
    
    public String generateResetPasswordToken(String userId) {
        Instant now = Instant.now();
    	return Jwts.builder()
                .subject(userId)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(SecurityConstants.PASSWORD_EXPIRATION_TIME)))
                .signWith(key)
                .compact();
    }
    public String extractUsername(String token) {

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        }
}
