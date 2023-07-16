package com.discern.api.dto;

import com.discern.api.enums.NoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {

    private Long id;
    private String title;
    private String text;
    @Enumerated(EnumType.STRING)
    private NoteType noteType;
    private Long createdBy;
    private LocalDateTime createdOn;
    private Long projectId;
    private Long companyId;

}
