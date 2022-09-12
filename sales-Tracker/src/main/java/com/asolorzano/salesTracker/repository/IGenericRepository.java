package com.asolorzano.salesTracker.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

//para crear una interfaz generica que se invoca en las demas interfaces
//t= entidad
//ID = primary key

@NoRepositoryBean
public interface IGenericRepository<T,ID> extends JpaRepository<T,ID> {
}
