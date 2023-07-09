package com.discern.api.dto;

import com.discern.api.model.Company;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerRegistrationDTO {

    UserDTO userDTO;
    CompanyDTO companyDTO;
}
