package com.crud.crudwithchangelog.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
@Service
public class JwtService {
    private static final String SECRET = "fs4EpnZOkmwd2UGsmoSJhU9q3cf7SplkWlXU+3Ip28SvtyCcOcddSTyLLczUYBoVJAM2zhz7WFr8OtYSSv9E4g==";
    private static final long EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(30);

    public String generateToken(UserDetails user) {
        Map<String,String> claims = new HashMap<>();
        claims.put("Who am I ", user.getUsername());
        return Jwts.builder()
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(EXPIRATION_TIME)))
                .signWith(getSecretKey())
                .compact();

    }
    private SecretKey getSecretKey() {
        byte[] decodeKey = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(decodeKey);
    }

    public String extractUsername(String token){
        Claims claims = getClaims(token);
        return claims.getSubject();

    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();


    }

    public boolean isTokenValid(String jwt){
        Claims claims = getClaims(jwt);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }

}
