package com.asolorzano.salesTracker.model;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Client {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClient;

    @Column(length = 100, nullable = false)
    private String firstName;

    @Column(length = 100, nullable = false)
    private String lastName;

    @Column(length = 10, nullable = false)
    private String cardId;

    @Column(length = 15, nullable = false)
    private String phoneNumber;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 150)
    private String address;
}

