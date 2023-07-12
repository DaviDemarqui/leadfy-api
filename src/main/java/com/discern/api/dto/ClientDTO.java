package com.discern.api.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private Long id;

    private String name;

    private String description;

    private byte[] photo;

    private String email;

    private String phone;

    private Long projectId;

    private Long companyId;
}
