package com.discern.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Davi
 * @created 01/ago./2023
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ProjectType {

    CLIENT("Client Project"),
    INTERNAL("Internal Project");

    private String description;
}
