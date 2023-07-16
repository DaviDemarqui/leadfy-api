package com.discern.api.dto;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
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

    @NotNull
    private String email;

    @NotNull
    private String username;

    @NotNull
    private String password;
}
