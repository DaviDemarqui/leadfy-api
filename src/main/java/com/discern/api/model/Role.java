package com.discern.api.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author Davi
 * @created 21/jul./2023
 */

@Entity
@Getter
@Setter
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
