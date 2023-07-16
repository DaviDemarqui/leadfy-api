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
    @Column
    private Long profileId;

    @Column
    private Long teamId;

    @OneToOne
    @JoinColumn(name = "profileId", referencedColumnName = "id")
    private Profile profile;
}
