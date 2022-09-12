package com.asolorzano.salesTracker.model;


import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class IngressDetailPk implements Serializable {


    @ManyToOne
    @JoinColumn(name = "id_ingress", nullable = false)
    private Ingress ingress;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

}
