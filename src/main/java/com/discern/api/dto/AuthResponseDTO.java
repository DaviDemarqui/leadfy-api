package com.discern.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Davi
 * @created 21/jul./2023
 */

@Data
@AllArgsConstructor
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType;
}
