package com.discern.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UserRoles {

    ROLE_CLIENT("Role Client"),
    ROLE_CLIENT_READ("Role Client"),
    ROLE_CLIENT_CREATE("Role Client"),
    ROLE_CLIENT_UPDATE("Role Client"),
    ROLE_CLIENT_DELETE("Role Client"),

    ROLE_TASK("Role Task"),
    ROLE_TASK_READ("Role Task"),
    ROLE_TASK_CREATE("Role Task"),
    ROLE_TASK_UPDATE("Role Task"),
    ROLE_TASK_DELETE("Role Task"),

    ROLE_NOTE("Role Note"),
    ROLE_NOTE_READ("Role Note"),
    ROLE_NOTE_CREATE("Role Note"),
    ROLE_NOTE_UPDATE("Role Note"),
    ROLE_NOTE_DELETE("Role Note"),

    ROLE_PROFILE("Role Profile"),

    ROLE_PROJECT("Role Project"),
    ROLE_PROJECT_READ("Role Project"),
    ROLE_PROJECT_CREATE("Role Project"),
    ROLE_PROJECT_UPDATE("Role Project"),
    ROLE_PROJECT_DELETE("Role Project"),

    ROLE_TEAM_MEMBER("Role Team Member"),

    ROLE_TEAM_MEMBER_READ("Role Team Member"),
    ROLE_TEAM_MEMBER_CREATE("Role Team Member"),
    ROLE_TEAM_MEMBER_UPDATE("Role Team Member"),
    ROLE_TEAM_MEMBER_DELETE("Role Team Member");

    private String description;
}
