package com.discern.api.dto;

import com.discern.api.enums.ProjectType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;


/**
 * @author Davi
 * @created 12/jul./2023
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCreationDTO {

    private String name;
    private String brief;
    private String manager;

    @Enumerated(EnumType.STRING)
    private ProjectType type;
    private Boolean status;
    private LocalDate dueDate;
    private Boolean ongoing;

    private Long companyId;

}
