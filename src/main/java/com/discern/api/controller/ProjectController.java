package com.discern.api.controller;

import com.discern.api.dto.ProjectCreationDTO;
import com.discern.api.dto.ProjectDTO;
import com.discern.api.service.ProjectService;
import com.discern.api.view.ListProjectVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Davi
 * @created 11/jul./2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_PROJECT_READ')")
    public ResponseEntity<?> getAllProject(ListProjectVO projectVO, Pageable pageable) {
//        tokenVerifyingService.validateCompanyId(token, projectDTO.getCompanyId());
        return ResponseEntity.ok(projectService.getAllProjects(projectVO, pageable));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_PROJECT_READ')")
    public ResponseEntity<?> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.findById(id));
    }

    @GetMapping("info/{id}")
    @PreAuthorize("hasRole('ROLE_PROJECT_READ')")
    public ResponseEntity<?> getProjectInfo(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.findProjectInfoById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_PROJECT_CREATE')")
    public ResponseEntity<?> postClient(@RequestBody ProjectCreationDTO projectCreationDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(projectService.createProject(projectCreationDTO));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_PROJECT_UPDATE')")
    public ResponseEntity<?> putClient(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        return ResponseEntity.ok(projectService.updateProject(projectDTO, id));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_PROJECT_DELETE')")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
