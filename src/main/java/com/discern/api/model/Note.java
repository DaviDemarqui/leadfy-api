package com.discern.api.model;


import com.discern.api.enums.NoteType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "note")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String text;

    @Column
    @Enumerated(EnumType.STRING)
    private NoteType noteType;

    @Column
    private Long createdBy;

    @Column
    private LocalDateTime createdOn;

    @Column
    private Long projectId;

    @Column
    private Long companyId;
}
