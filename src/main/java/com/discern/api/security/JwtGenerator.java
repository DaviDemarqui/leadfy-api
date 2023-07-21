package com.discern.api.security;

import com.discern.api.model.UserEntity;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Davi
 * @created 21/jul./2023
 */

@Component
public class JwtGenerator {

    public String generateToken(Long companyId, String email, Long userId, UserEntity user) {
        LocalDate expirationDate = LocalDate.now().plusWeeks(SecurityConstants.JWT_EXPIRATION_WEEKS);
        Date expirationTime = Date.from(expirationDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expirationTime)
                .claim("companyId", companyId)
                .claim("userId", userId)
                .claim("roles", user.getRoles())
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.JWT_SECRET)
                .compact();

        return token;
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SecurityConstants.JWT_SECRET)
                    .parseClaimsJws(token)
                    .getBody();

            LocalDateTime expirationDateTime = claims.getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.systemDefault());

            // Check if the token expiration time is in the future (considering the allowed clock skew)
            if (expirationDateTime.isBefore(currentDateTime.minusYears(SecurityConstants.ALLOWED_CLOCK_SKEW_MILLIS))) {
                throw new ExpiredJwtException(null, null, "JWT expired.");
            }

            return true;
        } catch (ExpiredJwtException ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT expired.");
        } catch (JwtException ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT invalid or tampered.");
        }
    }


    public String getEmailFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token).getBody();
        String email = claims.getSubject();
        return email;
    }


    public Long getCompanyIdFromToken(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.get("companyId", Long.class);
    }

    public Long getUserIdFromToken(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.get("userId", Long.class);
    }
}
