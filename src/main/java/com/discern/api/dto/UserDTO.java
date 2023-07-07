package com.discern.api.dto;

import lombok.*;

import java.util.Date;

/**
 * @Author Davi Demarqui
 * @Created 20/jun./2023
 */

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String email;
    private String username;
    private String password;
    private boolean status;
    private Date createdAt;
    private Date updatedAt;
}
