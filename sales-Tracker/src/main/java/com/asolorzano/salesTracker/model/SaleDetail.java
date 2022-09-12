package com.asolorzano.salesTracker.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class SaleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSaleDetail;

    @ManyToOne
    // el foreignkey =@..... refuerza la relacion pero funciona sin el
    @JoinColumn (name = "id_sale", nullable = false, foreignKey = @ForeignKey(name = "fk_detail_sale"))
    private Sale sale;

    @ManyToOne
    @JoinColumn (name = "id_product", nullable = false)
    private Product product;

    @Column(nullable = false)
    private short quantity;

    @Column(columnDefinition = "decimal(5,2)", nullable = false)
    private double  salePrice;

    @Column(columnDefinition = "decimal(5,2)", nullable = false)
    private double discount;

}
