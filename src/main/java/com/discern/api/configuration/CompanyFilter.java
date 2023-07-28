package com.discern.api.configuration;

import com.discern.api.exceptions.ProfileNotFoundException;
import com.discern.api.model.Profile;
import com.discern.api.repository.ProfileRepository;
import com.discern.api.security.JwtGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpRequest;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Davi
 * @created 28/jul./2023
 */

@RequiredArgsConstructor
public class CompanyFilter extends GenericFilterBean {

    private final JwtGenerator jwtGenerator;
    private final ProfileRepository profileRepository;
    private final static ThreadLocal<Long> currentCompanyId = new ThreadLocal<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = httpRequest.getHeader("Authorization");

        Long companyIdHeader = jwtGenerator.getCompanyIdFromToken(token);
        Long userIdHeader = jwtGenerator.getUserIdFromToken(token);

        Profile profile = profileRepository.findById(userIdHeader)
                .orElseThrow(ProfileNotFoundException::new);

        if(!Objects.equals(profile.getCompanyId(), companyIdHeader)) {
            throw new ServletException("The user does not belong to this company");
        }

        if(companyIdHeader != null) {
            try {
                currentCompanyId.set(companyIdHeader);
            } catch (NumberFormatException e) {
                throw new ServletException("Invalid companyId format");
            }
        }

        chain.doFilter(request, response);
    }

    public static Long getCurrentCompanyId() {
        return currentCompanyId.get();
    }

}
