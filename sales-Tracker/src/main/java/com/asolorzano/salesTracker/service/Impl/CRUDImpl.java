package com.asolorzano.salesTracker.service.Impl;

import com.asolorzano.salesTracker.model.Category;
import com.asolorzano.salesTracker.repository.IGenericRepository;
import com.asolorzano.salesTracker.service.ICRUD;

import java.util.List;

// esta clase se crea para que funcione la interfaz de ICRUD Y ES PARA QUE FUNCIONE PARA TODO
public abstract class CRUDImpl<T, ID> implements ICRUD<T,ID> {

    protected abstract IGenericRepository<T, ID> getRepo();

    @Override
    public T create(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public List<T> readAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T readById(ID id) throws Exception {
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepo().deleteById(id);
    }

    //public abstract List<Category> findByNameLike(String name);
}

