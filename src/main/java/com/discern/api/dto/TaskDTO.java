package com.discern.api.dto;

import com.discern.api.enums.Priority;
import com.discern.api.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long id;

    private String name;

    private String details;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private Long createdBy;

    private Long assignTo;

    private LocalDate dueDate;

    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private Long projectId;

    private Long companyId;
}
