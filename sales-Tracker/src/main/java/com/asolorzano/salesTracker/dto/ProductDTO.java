package com.asolorzano.salesTracker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

        private Integer idProduct;
        private Integer idCategory;
        private String name;
        private String description;
        private double price;
        private boolean enabled;
}
