package com.discern.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {

    private Long id;
    private String title;
    private String text;
    private Long createdBy;
    private LocalDateTime createdOn;
    private Long projectId;
}
