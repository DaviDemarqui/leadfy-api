package com.discern.api.dto;

import com.discern.api.enums.Priority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long id;

    private String name;

    private String details;

    @Enumerated
    private Priority priority;

    private Long createdBy;

    private Long assignTo;

    private LocalDateTime dueDate;

    private Boolean status;

    private Long projectId;

    private Long companyId;
}
