package com.discern.api.service;

import com.discern.api.dto.TaskDTO;
import com.discern.api.dto.TaskStatusDTO;
import com.discern.api.enums.TaskStatus;
import com.discern.api.exceptions.TaskNotFoundException;
import com.discern.api.model.Task;
import com.discern.api.repository.TaskRepository;
import com.discern.api.utils.mapper.MapperUtil;
import com.discern.api.utils.matcher.TiposExampleMatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {

    private final MapperUtil mapperUtil;
    private final TaskRepository taskRepository;

    public Page<TaskDTO> getAllTasks(TaskDTO taskDTO, Pageable pageable) {
        var example = Example.of(mapperUtil.mapTo(taskDTO, Task.class),
                TiposExampleMatcher.exampleMatcherMatchingAny());

        return taskRepository.findAll(example, pageable)
                .map(Task -> mapperUtil.mapTo(Task, TaskDTO.class));
    }

    public TaskDTO findById(Long id) {
        return mapperUtil.mapTo(taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new), TaskDTO.class);
    }

    public void completeTasks(List<TaskStatusDTO> taskStatusList) {
        for(TaskStatusDTO taskDTO : taskStatusList) {
            Task task = taskRepository.findById(taskDTO.getTaskId()).orElseThrow(TaskNotFoundException::new);
            task.setStatus(TaskStatus.COMPLETED);
            taskRepository.save(task);
        }
    }

    public TaskDTO saveOrUpdate(TaskDTO taskDTO, Long id) {
        Task taskSave;

        if(id != null) {
            taskSave = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
            Task pDTO = mapperUtil.mapTo(taskDTO, Task.class);
            BeanUtils.copyProperties(pDTO, taskSave, "id");
        } else {
            taskSave = mapperUtil.mapTo(taskDTO, Task.class);
        }

        return mapperUtil.mapTo(taskRepository.save(taskSave), TaskDTO.class);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
