package com.discern.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Priority {

    VERY_HIGH("Very high"),
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low"),
    VERY_LOW("Very Low");

    private String description;
}
