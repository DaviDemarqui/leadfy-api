package com.discern.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String cpf;

//    @Column(nullable = false)
//    private boolean status;

//    @Column(name = "created_at")
//    private Date createdAt;
//
//    @Column(name = "updated_at")
//    private Date updatedAt;
}
