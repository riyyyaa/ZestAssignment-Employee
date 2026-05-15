package com.zest.employee_management.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY =
            "mysecretkeymysecretkeymysecretkey12";

    private final long EXPIRATION_TIME =
            1000 * 60 * 60;

    private Key getSigningKey() {

        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes()
        );
    }

    public String generateToken(String username) {

        return Jwts.builder()

                .setSubject(username)

                .setIssuedAt(new Date())

                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + EXPIRATION_TIME
                        )
                )

                .signWith(
                        getSigningKey(),
                        SignatureAlgorithm.HS256
                )

                .compact();
    }

    public String extractUsername(String token) {

        return extractClaims(token)
                .getSubject();
    }

    public Claims extractClaims(String token) {

        return Jwts.parserBuilder()

                .setSigningKey(getSigningKey())

                .build()

                .parseClaimsJws(token)

                .getBody();
    }

    public boolean validateToken(
            String token,
            String username
    ) {

        String extractedUsername =
                extractUsername(token);

        return extractedUsername.equals(username);
    }
}