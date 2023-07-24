package com.discern.api.configuration;

import com.discern.api.security.JwtGenerator;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Davi
 * @created 24/jul./2023
 */

@Configuration
@EnableAspectJAutoProxy
public class JpaFilterConfig {

    private EntityManager entityManager;
    private HttpServletRequest request;
    private  JwtGenerator jwtGenerator;

    public JpaFilterConfig(EntityManager entityManager, HttpServletRequest request, JwtGenerator jwtGenerator) {
        this.entityManager = entityManager;
        this.request = request;
        this.jwtGenerator = jwtGenerator;
    }

    @Before(value = "execution(* com.discern.api.configuration..*(..))")
    public void setCompanyFilter() {

        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        Long companyId = 0L; // Default companyId if not found in the token
        if (authorization != null && authorization.startsWith("Bearer ")) {
            try {
                String token = authorization.substring("Bearer ".length());
                companyId = jwtGenerator.getCompanyIdFromToken(token);
            } catch (Exception ignored) {
            }
        }

        try (Session session = entityManager.unwrap(Session.class)) {
            session.enableFilter("company")
                    .setParameter("companyIdParam", companyId);
        } catch (Exception ignored) {
        }
    }
}
