package com.discern.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "company")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private byte[] logo;

    @Column
    private String email;

    @Column
    private String industry;

    @Column
    private String phone;

    @Column
    private LocalDateTime createdOn;

    @Column
    private String cnpj;

    @Column
    private String address;
}
