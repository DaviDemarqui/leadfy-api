package com.discern.api.controller;

import com.discern.api.dto.TaskDTO;
import com.discern.api.dto.TaskStatusDTO;
import com.discern.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Davi
 * @created 13/jul./2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tasks")
@PreAuthorize("hasRole('ROLE_TASK')")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<?> getAllTasks(TaskDTO taskDTO, Pageable pageable) {
//        tokenVerifyingService.validateCompanyId(token, taskDTO.getCompanyId());
        return ResponseEntity.ok(taskService.getAllTasks(taskDTO, pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getAllTasks(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @PostMapping("status")
    public ResponseEntity<?> postTask(@RequestBody List<TaskStatusDTO> taskDTO) {
        taskService.completeTasks(taskDTO);
        return ResponseEntity.ok("Tasks Completed");
    }

    @PostMapping
    public ResponseEntity<?> postTask(@RequestBody TaskDTO taskDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(taskService.saveOrUpdate(taskDTO, null));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> putTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.saveOrUpdate(taskDTO, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
