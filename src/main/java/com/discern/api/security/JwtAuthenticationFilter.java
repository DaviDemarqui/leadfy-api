package com.discern.api.security;

import com.discern.api.exceptions.ProfileNotFoundException;
import com.discern.api.model.Profile;
import com.discern.api.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Davi
 * @created 21/jul./2023
 */

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    private final static ThreadLocal<Long> currentCompanyId = new ThreadLocal<>();


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = getJwtFromRequest(request);

        if(StringUtils.hasText(token) && jwtGenerator.validateToken(token)) {
            String email = jwtGenerator.getEmailFromJWT(token);

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authenticationToken = new
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            Long companyIdHeader = jwtGenerator.getCompanyIdFromToken(token);

            Profile profile = profileRepository.findById(jwtGenerator.getUserIdFromToken(token))
                    .orElseThrow(ProfileNotFoundException::new);//TODO - Test this block of code!

            if(!Objects.equals(profile.getCompanyId(), companyIdHeader)) {
                throw new ServletException("The user do not belong to this company");
            }
            try {
                currentCompanyId.set(companyIdHeader);
            } catch (NumberFormatException e) {
                throw new ServletException("Invalid companyId format");
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        } else {
            return null;
        }
    }

    public static Long getCurrentCompanyId() {
        return currentCompanyId.get();
    }
}
