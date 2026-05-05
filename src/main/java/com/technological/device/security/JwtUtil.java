package com.technological.device.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;


@Component
public class JwtUtil {

    private final String SECRET = "mi_clave_super_secreta_123456789";

    private final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes());
    // genera token
    public String generateToken(String email) {

        Instant now = Instant.now(); // toma la hora actual del sistema

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(Date.from(now)) // cuando se creo el token
                .setExpiration(Date.from(now.plusSeconds(3600))) // 1 hora expira
                .signWith(SECRET_KEY) // firma el token con clave segura
                .compact();
    }

    // extrae el token
    public  String extraerEmail(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public  boolean isTokenValid(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);

            return true;
        }catch (Exception e){
            return false;
        }

    }
}