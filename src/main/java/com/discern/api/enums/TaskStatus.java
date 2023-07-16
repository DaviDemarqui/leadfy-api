package com.discern.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TaskStatus {

    TO_DO("To-do"),
    IN_PROGRESS("In Progress"),
    MISSED_DEADLINE("Missed Deadline"),
    COMPLETED("Completed");

    private String description;
}
