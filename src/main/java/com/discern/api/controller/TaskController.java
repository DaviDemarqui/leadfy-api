package com.discern.api.controller;

import com.discern.api.dto.TaskDTO;
import com.discern.api.service.TaskService;
import com.discern.api.service.TaskService;
import com.discern.api.service.TokenVerifyingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Davi
 * @created 13/jul./2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TokenVerifyingService tokenVerifyingService;

    @GetMapping
    public ResponseEntity<?> getAllTasks(TaskDTO taskDTO, Pageable pageable) {
//        tokenVerifyingService.validateCompanyId(token, taskDTO.getCompanyId());
        return ResponseEntity.ok(taskService.getAllTasks(taskDTO, pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getAllTasks(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> postTask(@RequestBody TaskDTO taskDTO, @RequestHeader("Authorization") String token) {
        tokenVerifyingService.validateCompanyId(token, taskDTO.getCompanyId());
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
