package com.discern.api.utils;

import com.discern.api.exceptions.ProjectNotFoundException;
import com.discern.api.model.Project;
import com.discern.api.repository.ProjectRepository;
import com.discern.api.security.JwtAuthenticationFilter;
import com.discern.api.security.JwtGenerator;

import java.util.Objects;

/**
 * @author Davi
 * @created 28/jul./2023
 */

public class CompanyValidator {


    private static ProjectRepository projectRepository;
    private static JwtGenerator jwtGenerator;

    /**
     * REMEMBER!
     * The Long id in this file is the companyId!
     */

    public static void validateId(Long id) {
        if(!Objects.equals(id, JwtAuthenticationFilter.getCurrentCompanyId())) {
            throw new SecurityException("The companyId is not valid!");
        }
    }

    public static void validateProject(Long projectId, Long id) {
        Project project = projectRepository.findByIdAndCompanyId(projectId, id)
                .orElseThrow(ProjectNotFoundException::new);
        if(!Objects.equals(project.getCompanyId(), JwtAuthenticationFilter.getCurrentCompanyId())) {
            throw new SecurityException("The project does not belong to that company");
        }
    }

    // Deve ser usado quando entidate não tiver o companyId;
    public static void validateCompanyToken(String token) {
        if(!Objects.equals(jwtGenerator.getCompanyIdFromToken(token), JwtAuthenticationFilter.getCurrentCompanyId())) {
            throw new SecurityException("");
        }
    }
}
