package com.asolorzano.salesTracker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleDTO {

    private Integer idSale;
    private ClientDTO client;
    private UserDTO user;
    private LocalDateTime datetime;
    private double total;
    private double tax;
    private boolean enabled;
    private List<SaleDetailDTO> details;
}
