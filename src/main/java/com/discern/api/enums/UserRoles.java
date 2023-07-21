package com.discern.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UserRoles {

    ROLE_CLIENT("Role Client"),
    ROLE_TASK("Role Task"),
    ROLE_NOTE("Role Note"),
    ROLE_PROFILE("Role Profile"),
    ROLE_PROJECT("Role Project"),
    ROLE_TEAM_MEMBER("Role Team Member");

    private String description;
}
