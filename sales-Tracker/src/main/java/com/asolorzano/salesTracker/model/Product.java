package com.asolorzano.salesTracker.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduct;

    @ManyToOne //FK
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;

    @Column (length = 50, nullable = false)
    private String name;

    @Column (length = 150, nullable = false)
    private String description;

    @Column(columnDefinition = "decimal(5,2)", nullable = false)
    private double price;

    private boolean enabled;


}
