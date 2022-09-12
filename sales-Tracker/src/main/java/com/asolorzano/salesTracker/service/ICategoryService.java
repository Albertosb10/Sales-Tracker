package com.asolorzano.salesTracker.service;

import com.asolorzano.salesTracker.model.Category;

import java.util.List;


public interface ICategoryService extends ICRUD<Category, Integer>{

    //para buscar por nombre
    List<Category> findByName(String name);


    /*
    los borro para extenderlos de la interface ICRUD

    Category create(Category category) throws Exception;
    Category update(Category category) throws Exception;
    List<Category> readAll() throws Exception;
    Category readById(Integer id) throws Exception;
    void delete(Integer id) throws Exception;

     */

    List<Category> findByNameLike(String name);

}

