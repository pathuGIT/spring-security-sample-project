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

    public String generateToken(Users user) {
        System.out.println(user.getRole()+user.getUsername());
        return Jwts.builder()
                .claim("ROLE", user.getRole())  // Add role claim
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 5)) // 5 minutes
                .signWith(secretKey)
                .compact();
    }


    // Refresh token (7 days)
    public String generateRefreshToken(Users user) {
        return Jwts.builder()
                .claim("ROLE", user.getRole())  // Add role claim
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 7)) // 7 days
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

    public Date extractExpiration(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }

    public String extractRole(String token) {
        return (String) Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("ROLE");
    }


    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUserName(token);
        Date expiration = extractExpiration(token);
        return username.equals(userDetails.getUsername()) && !expiration.before(new Date());
    }

}
