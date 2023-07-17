package com.discern.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Privilegies {

    ROLE_PROJECT_READ("Role Project Lead");

    private String description;
}
