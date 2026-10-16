package com.example.jwtvalidation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JwtService {

    private final SecretKey secretKey;

    public JwtService(@Value("${jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Map<String, Object> validateToken(String token) {
        Claims claims = Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();

        Map<String, Object> result = new java.util.LinkedHashMap<>();
        result.put("username", claims.getSubject());
        result.put("roles", claims.get("roles"));
        result.put("issuedAt", claims.getIssuedAt());
        result.put("expiration", claims.getExpiration());
        return result;
    }

    public String createToken(String username, String role) {
        Date now = new Date();
        return Jwts.builder()
            .subject(username)
            .claim("roles", List.of(role))
            .issuedAt(now)
            .expiration(new Date(now.getTime() + 60 * 60 * 1000L))
            .signWith(secretKey)
            .compact();
    }
}
