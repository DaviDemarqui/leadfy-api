package com.discern.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;

@Entity
@Table(name = "client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FilterDef(name = "company", parameters = {@ParamDef(name = "companyId", type = "long")})
@Filter(name = "company", condition = "companyId = :companyId")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String inviteMessage;

    @Column
    private Long projectId;

    @Column
    private Long companyId;
}
