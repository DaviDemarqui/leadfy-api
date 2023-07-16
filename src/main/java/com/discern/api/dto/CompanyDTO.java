package com.discern.api.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    private Long id;

    @NotNull
    private String name;

    private String industry;

    @NotNull
    private String phone;

    private LocalDateTime createdOn;

    @NotNull
    private String address;
}
