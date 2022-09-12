package com.asolorzano.salesTracker.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {


    @Min(1)
    @Max(999)
    private Integer id;

    @NotNull
    @NotEmpty
    @Size(min =3, max=50)
    private String nameCategory;


    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String descriptionCategory;


    @NotNull
    private boolean enabledCategory;


}
