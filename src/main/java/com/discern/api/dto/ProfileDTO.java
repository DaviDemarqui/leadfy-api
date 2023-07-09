package com.discern.api.dto;


import lombok.*;

/**
 * @Author Davi Demarqui
 * @Created 20/jun./2023
 */

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {

    private Long id;
    private byte[] profilePic;
    private String name;
    private String email;
    private String company_role;
    private UserDTO user;
    private Long companyId;
    private Boolean status;

}
