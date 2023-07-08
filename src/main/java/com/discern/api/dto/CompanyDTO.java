package com.discern.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    private Long id;

    private String name;

    private byte[] logo;

    private String email;

    private String industry;

    private String phone;

    private LocalDateTime createdOn;

    private String cnpj;

    private String address;
}
