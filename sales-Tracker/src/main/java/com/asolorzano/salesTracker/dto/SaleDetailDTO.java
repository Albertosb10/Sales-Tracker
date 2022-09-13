package com.asolorzano.salesTracker.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleDetailDTO {

    //anotacion para que me ignore el Json
    @JsonIgnore
    private SaleDTO sale;
    private ProductDTO product;
    private short quantity;
    private double salePrice;
    private double discount;
}
