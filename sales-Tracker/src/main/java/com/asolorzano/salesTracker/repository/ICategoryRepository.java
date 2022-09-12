package com.asolorzano.salesTracker.repository;

import com.asolorzano.salesTracker.model.Category;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;


// se recomienda extender esta clase de cruDRepository, pide el primer dato el nombre de la tabla y el
// segundo el tipo de dato del dato Id

//no es necesario utilizar esta anotacion cuando se hace por el metodo generico
@Repository
public interface ICategoryRepository extends IGenericRepository<Category,Integer> {

    //metodo para buscar por el nombre
    //derivadedquery
    //SELECT * FROM CATEGORY WHERE NAME = ?
    //el siguiente paso es incluirlo en los servicios
    List<Category> findByName(String name);

    //SELECT * FROM CATEGORY WHERE NAME LIKE '%xx%'
    /*
    '%XYZ%' findByXXXConstains
    'XYZ%' findByXXXStartWith
    '%XYZ' findByXXXEndsWith
    */

    List<Category> findByNameLike(String name);


}
