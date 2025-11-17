package com.dryfruits.dryfruitsbackend.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 365; // 1 year

    public static String generateToken(String username, String phoneNo, String type) {
        return Jwts.builder()
                .setSubject(username)
                .claim("phoneNo", phoneNo)   // 👈 Add phone number as a claim
                .claim("type", type)   // 👈 Add type as a claim
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

}
