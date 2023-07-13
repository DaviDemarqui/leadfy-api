package com.discern.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private String brief;
    private LocalDate dueDate;
    private Boolean ongoing;
    private LocalDate createdOn;
    private Boolean status;
    private Long companyId;
}
