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
    private String username;
    private String role;
    private UserDTO user;

}
