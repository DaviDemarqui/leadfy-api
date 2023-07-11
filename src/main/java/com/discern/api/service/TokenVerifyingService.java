package com.discern.api.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.discern.api.security.JwtHelper;
import com.discern.api.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TokenVerifyingService {

    private final JwtHelper jwtHelper;
    private final JwtUtil jwtUtil;

    public String decoderCompany(String token) {
        token = token.replace("Bearer ", "");
        DecodedJWT decodedJWT = jwtHelper.decodeJwtToken(token);
        String empresaToken = decodedJWT.getClaim(JwtHelper.CLAIM_COMPANY).asString();

        return empresaToken;

    }

    public String decoderUser(String token) {
        token = token.replace("Bearer ", "");
        DecodedJWT decodedJWT = jwtHelper.decodeJwtToken(token);
        return decodedJWT.getClaim(JwtHelper.CLAIM_USER).asString();
    }

    public void validateCompanyId(String token, Long companyId) {
        token = token.replace("Bearer ", "");
        Long companyIdFromToken = jwtUtil.extractCompanyId(token).longValue();

        if(companyIdFromToken != companyId) {
            throw new RuntimeException("Token Error: Company Id not Match");
        }
    }

//    public void verificaEmpresaUsuario(Long idEmpresa, String token) {
//        token = token.replace("Bearer ", "");
//        DecodedJWT decodedJWT = jwtHelper.decodeJwtToken(token);
//        String empresaToken = decoderEmpresa(token);
//
//        if (!idEmpresa.toString().equals(empresaToken)) {
//            throw new UsuarioEmpresaInvalidaException();
//        }
//    }
}
