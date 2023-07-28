package com.discern.api.security;

import com.discern.api.enums.UserRoles;
import com.discern.api.model.Role;
import com.discern.api.model.UserEntity;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Davi
 * @created 21/jul./2023
 */

@Component
public class JwtGenerator {

    public String generateToken(Long companyId, String email, Long userId, UserEntity user) {
        // Set the token validity duration (e.g., 1 hour)
        int validityDurationInHours = 1;

        // Get the current LocalDateTime
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Calculate the expiration LocalDateTime by adding validityDurationInHours
        LocalDateTime expirationDateTime = currentDateTime.plusHours(validityDurationInHours);

        // Convert LocalDateTime to ZonedDateTime
        ZonedDateTime zonedDateTime = expirationDateTime.atZone(ZoneId.systemDefault());

        // Convert ZonedDateTime to Date
        Date expirationTime = Date.from(zonedDateTime.toInstant());

        List<String> roles = new ArrayList<>();

        for (Role role : user.getRoles()) {
            roles.add(role.getName());
        }

        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expirationTime)
                .claim("companyId", companyId)
                .claim("userId", userId)
                .claim("roles", roles)
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

    public Long validateAndReturnCompanyId(String token) {
            return getCompanyIdFromToken(token.substring(5));
    }
}
