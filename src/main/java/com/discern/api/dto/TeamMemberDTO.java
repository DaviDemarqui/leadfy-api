package com.discern.api.dto;

import com.sun.istack.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamMemberDTO {

    @NotNull
    private Long profileId;

    @NotNull
    private Long teamId;

    private ProfileDTO profile;
}
