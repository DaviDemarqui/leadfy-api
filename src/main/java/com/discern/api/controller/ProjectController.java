package com.discern.api.controller;

import com.discern.api.dto.ProjectDTO;
import com.discern.api.service.ClientService;
import com.discern.api.service.ProjectService;
import com.discern.api.service.TokenVerifyingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    private final TokenVerifyingService tokenVerifyingService;

    @GetMapping
    public ResponseEntity<?> getAllProject(ProjectDTO projectDTO, Pageable pageable) {
//        tokenVerifyingService.validateCompanyId(token, projectDTO.getCompanyId());
        return ResponseEntity.ok(projectService.getAllProjects(projectDTO, pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getAllProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> postClient(@RequestBody ProjectDTO projectDTO, @RequestHeader("Authorization") String token) {
        tokenVerifyingService.validateCompanyId(token, projectDTO.getCompanyId());
        return ResponseEntity.ok(projectService.saveOrUpdate(projectDTO, null));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> putClient(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        return ResponseEntity.ok(projectService.saveOrUpdate(projectDTO, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
