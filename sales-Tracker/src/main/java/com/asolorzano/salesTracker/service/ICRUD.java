package com.asolorzano.salesTracker.service;

import com.asolorzano.salesTracker.model.Category;

import java.util.List;

//lepaso la entidad y el identificador <T,ID >
public interface ICRUD<T, ID> {

    T create(T t) throws Exception;
    T update(T t) throws Exception;
    List<T> readAll() throws Exception;
    T readById(ID id) throws Exception;
    void delete(ID id) throws Exception;
}
