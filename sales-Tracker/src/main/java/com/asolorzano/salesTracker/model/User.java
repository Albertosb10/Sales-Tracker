package com.asolorzano.salesTracker.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
//para cambiar el nombre de la BD
@Table(name = "user_data")
public class User {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY )
    private Integer idUser;

    /*
    //cardinalidad
    @ManyToMany
    //se establecio la relacion de las tablas user y role
    //como es una relacion muchos a muchos se genero una nueva tabla "user role"
    @JoinTable(name = "user role",
        joinColumns = @JoinColumn(name = "id user"),
        inverseJoinColumns = @JoinColumn(name = "id role"))
    private List<Role> role;
    */


    @ManyToOne //FK
    @JoinColumn(name = "id_role", nullable = false)
    private Role role;

    @Column(length = 50, nullable = false)
    private String userName;

    @Column(length = 50, nullable = false)
    private String password;

    private boolean enabled;

}