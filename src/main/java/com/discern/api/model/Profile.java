package com.discern.api.model;

import com.discern.api.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Author Davi Demarqui
 * @Created 18/jun./2023
 */

@Entity
@Table(name = "profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String company_role;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Long companyId;

    @Column
    private Boolean status;

}
