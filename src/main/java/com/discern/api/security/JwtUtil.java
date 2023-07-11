package com.discern.api.security;

import com.auth0.jwt.algorithms.Algorithm;
import com.discern.api.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;


import javax.annotation.PostConstruct;
import java.util.function.Function;

/*
 *  @author DaviDemarqui
 *  @github https://github.com/DaviDemarqui
 */
@Service
public class JwtUtil {
    private final byte[] jwtSigningKey = Base64.getEncoder().encode("32753284".getBytes());

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

//    public String extractCompanyId(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }

    public Integer extractCompanyId(String token) {
        Claims claims = extractAllClaims(token);
        return (Integer) claims.get("companyId");
    }

    public Integer extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        return (Integer) claims.get("userId");
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return Jwts.parser().setSigningKey(jwtSigningKey).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(Long companyId, String email, Long userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("companyId", companyId);
        claims.put("userId", userId);
        claims.put("email", email);
        return createToken(claims, email);
    }

    private String createToken(Map<String, Object> claims, String email) {

        return Jwts.builder().setClaims(claims).setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, jwtSigningKey).compact();
    }

    public Boolean validateToken(String token, User user) {
        final String email = extractEmail(token);
        Boolean teste = (email.equals(user.getEmail()) && !isTokenExpired(token));
        return teste;
    }
}