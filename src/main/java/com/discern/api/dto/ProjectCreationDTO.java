package com.discern.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String members;
    private String tag;
    private Boolean status;
    private LocalDate dueDate;
    private Boolean ongoing;

    private Long companyId;

}
