package com.discern.api.model;

import com.discern.api.enums.Priority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String details;

    @Column
    @Enumerated
    private Priority priority;

    @Column
    private Long createdBy;

    @Column
    private Long assignTo;

    @Column
    private LocalDate dueDate;

    @Column
    private LocalDate createdAt;

    @Column
    private String status;

    @Column
    private Long projectId;

    @Column
    private Long companyId;
}
