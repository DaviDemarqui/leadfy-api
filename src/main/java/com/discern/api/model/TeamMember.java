package com.discern.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "team_member")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamMember {

    @Id
    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "team_id")
    private Long teamId;

    @OneToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false, referencedColumnName = "id")
    private Team team;

    @OneToOne
    @JoinColumn(name = "profile_id", insertable = false, updatable = false, referencedColumnName = "id")
    private Profile profile;
}
