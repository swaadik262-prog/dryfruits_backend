package com.dryfruits.dryfruitsbackend.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Base64;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String base64Secret; // base64 encoded 32-byte key

    private Key key;

    @PostConstruct
    public void init() {
        // Decode base64 secret and build proper key for HS256
        byte[] secretBytes = Base64.getDecoder().decode(base64Secret);
        this.key = Keys.hmacShaKeyFor(secretBytes);
    }

    private Claims parseClaims(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsername(String token) {
        try {
            return parseClaims(token).getSubject();
        } catch (JwtException e) {
            return null;
        }
    }

    public Date extractExpiration(String token) {
        try {
            return parseClaims(token).getExpiration();
        } catch (JwtException e) {
            return null;
        }
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = parseClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(String phoneNo, int userId) {
        Map<String, Object> claims = Map.of("userId", userId);
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(phoneNo)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 1000L * 60 * 60 * 24 * 365)) // 1 year
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token, String inputUsername) {
        try {
            final String username = getUsername(token);
            final Date expiration = extractExpiration(token);
            return username != null && username.equals(inputUsername) && expiration != null && expiration.after(new Date());
        } catch (JwtException e) {
            return false;
        }
    }
}
