package com.discern.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Davi
 * @created 12/jul./2023
 */

// DTO usado para melhor config sem precisar salvar info
// desnecessaria na tabela de clientes;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCreationDTO {

    private ClientDTO clientDTO;

    private Boolean createProject;

}
