package com.asolorzano.salesTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderDTO {

    private Integer idProvider;
    private String Name;
    private String address;

}
