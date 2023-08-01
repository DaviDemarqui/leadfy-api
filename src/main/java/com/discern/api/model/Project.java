package com.discern.api.model;

import com.discern.api.enums.ProjectType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FilterDef(name = "company", parameters = {@ParamDef(name = "companyId", type = "long")})
@Filter(name = "company", condition = "company_id = :companyId")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private LocalDate dueDate;

    @Column
    private Boolean timeTracking;

    @Column
    private LocalDate createdOn;

    @Column
    private Boolean status;

    @Column
    @Enumerated(EnumType.STRING)
    private ProjectType type;

    private Long manager;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "created_by")
    private Long createdBy;
}
