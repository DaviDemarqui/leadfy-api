package com.discern.api.utils;

import com.discern.api.security.JwtAuthenticationFilter;

import javax.servlet.ServletException;
import java.util.Objects;

/**
 * @author Davi
 * @created 28/jul./2023
 */
public class CompanyValidator {

    public static boolean validateId(Long id) {
        if(!Objects.equals(id, JwtAuthenticationFilter.getCurrentCompanyId())) {
            throw new SecurityException("The companyId is not valid!");
        }
        return true;
    }
}
