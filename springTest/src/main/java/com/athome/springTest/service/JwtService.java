package com.athome.springTest.service;

import com.athome.springTest.model.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private final SecretKey secretKey;

    public JwtService() {
        String secret = "mPJr1hN9/kpU32l3zDnGqG0G0pX5KZs9zR3Bv6nLcc0=";
        this.secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
    }

    public String generateToken(Users users){
        Map<String, Object> claims = new HashMap<>();
        claims.put("ROLE", users.getRole());

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(users.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000*60*5))
                .and()
                .signWith(secretKey)
                .compact();
    }

    public String extractUserName(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()));
    }
}
