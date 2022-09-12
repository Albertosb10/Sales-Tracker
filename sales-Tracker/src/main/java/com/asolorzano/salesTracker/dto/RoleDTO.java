package com.asolorzano.salesTracker.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
//Para dejar que pasen los campos nulos
// @JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDTO {

    private Integer idRole;
    private String name;
    private boolean enabled;

}
