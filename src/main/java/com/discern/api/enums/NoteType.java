package com.discern.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum NoteType {
    PERSONAL("Personal Note"),
    PROJECT("Project Note");

    private String description;
}
