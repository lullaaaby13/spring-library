package com.lullaby.springlibrary.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;


@Slf4j
@Component
public class JwtProvider {

    private final Key key;
    private final Long accessTokenExpiration;
    private final Long refreshTokenExpiration;
    private final JwtParser jwtParser;

    public JwtProvider(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.access-token-expiration}") Long accessTokenExpiration,
            @Value("${jwt.refresh-token-expiration}") Long refreshTokenExpiration
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
        this.jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }

    public String createAccessToken(Long userId) {
        Claims claims = Jwts.claims();
        claims.put("userId", userId);

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime expiration = now.plusSeconds(accessTokenExpiration);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(expiration.toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String createRefreshToken(Long userId) {
        Claims claims = Jwts.claims();
        claims.put("userId", userId);

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime expiration = now.plusSeconds(refreshTokenExpiration);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(expiration.toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException | IllegalArgumentException | UnsupportedJwtException |
                 ExpiredJwtException e) {
            return false;
        }
    }

    public Long parse(String accessToken) {
        try {
            Claims body = this.jwtParser.parseClaimsJws(accessToken).getBody();
            return ((Integer) body.get("userId")).longValue();
        } catch (ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

}
