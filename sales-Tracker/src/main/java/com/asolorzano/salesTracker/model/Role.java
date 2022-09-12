package com.asolorzano.salesTracker.model;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Role {

    @Id
    private Integer idRole;

    @Column(length = 10, nullable = false)
    private String name;

    private boolean enabled;

}
