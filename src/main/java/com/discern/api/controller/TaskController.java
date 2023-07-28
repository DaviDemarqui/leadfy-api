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
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_TASK_READ')")
    public ResponseEntity<?> getAllTasks(Pageable pageable) {
        return ResponseEntity.ok(taskService.getAllTasks(pageable));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_TASK_READ')")
    public ResponseEntity<?> getAllTasks(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @PostMapping("status")
    @PreAuthorize("hasRole('ROLE_TASK_UPDATE')")
    public ResponseEntity<?> postTask(@RequestBody List<TaskStatusDTO> taskDTO) {
        taskService.completeTasks(taskDTO);
        return ResponseEntity.ok("Tasks Completed");
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_TASK_CREATE')")
    public ResponseEntity<?> postTask(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.saveOrUpdate(taskDTO, null));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_TASK_UPDATE')")
    public ResponseEntity<?> putTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.saveOrUpdate(taskDTO, id));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_TASK_DELETE')")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
