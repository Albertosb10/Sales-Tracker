package com.asolorzano.salesTracker.model;


import lombok.Data;
import lombok.experimental.UtilityClass;

import javax.persistence.*;

@Data
@Entity
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProvider;

    @Column(length = 50, nullable = false)
    private String  name;

    @Column(length = 50, nullable = false)
    private String address;

    private  boolean enabled;
}
