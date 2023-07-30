package com.discern.api.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Immutable
@Entity
@Table(name = "project_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectInfoVO {

    @Id
    private Long projectId;
    private String projectName;
    private String projectDescription;
    private LocalDate dueDate;
    private Integer numTasksCompleted;
    private Integer numTasksTodo;
    private Integer diasRestantes;
    private Integer numMembros;
    private Integer conclusionPercentage;
    private Long companyId;
}
