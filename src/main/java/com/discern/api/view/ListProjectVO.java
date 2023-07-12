package com.discern.api.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author Davi
 * @created 12/jul./2023
 */

@Immutable
@Entity
@Table(name = "v_list_project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListProjectVO {

    @Id
    private Long projectId;
    private String projectName;
    private String projectDescription;
    private Boolean projectStatus;
    private Long companyId;
    private BigDecimal percentage; // Porcentagem de conclusão do projeto;
}
