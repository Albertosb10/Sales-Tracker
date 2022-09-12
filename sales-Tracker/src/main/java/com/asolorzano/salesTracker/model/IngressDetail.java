package com.asolorzano.salesTracker.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Column;
import javax.persistence.Id;

@Data
@Entity
@IdClass(IngressDetailPk.class)
public class IngressDetail {


        @Id
        private Ingress ingress;

        @Id
        private Product product;

        @Column(nullable = false)
        private short quantity;

        @Column(columnDefinition = "decimal(5,2)", nullable = false)
        private double cost;

}
