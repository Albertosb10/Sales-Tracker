package com.asolorzano.salesTracker.model;

import lombok.Data;

//en produccion no es permitido import.*, por ende se importa por separado
import javax.persistence.*;

@Data
@Entity
@Table(name = "categories")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategory;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 150, nullable = false)
    private String description;

    private boolean enabled;


}
