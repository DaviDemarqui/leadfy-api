package com.discern.api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class JwtHelper {

    private String secret = "32753284";
    public static final String CLAIM_COMPANY = "company";
    public static final String CLAIM_USER = "profile";

    public static final String CLAIM_USER_ID = "profileId";
    public static final String CLAIM_COMPANY_ID = "companyId";



    private Algorithm algorithm;

    public JwtHelper() {

    }

    @PostConstruct
    private void postConstruct() {
        algorithm = Algorithm.HMAC256(secret.getBytes());
    }

    public DecodedJWT decodeJwtToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }
}
