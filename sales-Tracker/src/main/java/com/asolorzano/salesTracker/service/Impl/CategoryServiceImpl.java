package com.asolorzano.salesTracker.service.Impl;


import com.asolorzano.salesTracker.model.Category;
import com.asolorzano.salesTracker.repository.ICategoryRepository;
import com.asolorzano.salesTracker.repository.IGenericRepository;
import com.asolorzano.salesTracker.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// ya no vamos a implementar si no vamos a extenderpor ende se elimima la siguiente line esto para poder hacer funcionar el
//crudimpl
//public class CategoryServiceImpl extends CRUDImpl<Category, Integer> implements ICategoryService {
public class CategoryServiceImpl extends CRUDImpl<Category, Integer> implements ICategoryService {

    //para evitar estar instanciando
    @Autowired
    private ICategoryRepository repo;

    protected IGenericRepository<Category, Integer> getRepo(){
        return repo;
    }

    @Override
    public List<Category> findByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public List<Category> findByNameLike(String name) {
        return repo.findByNameLike("%" + name + "%");
    }

    /* ** estas anotaciones se borran pues se estan imbocando por la extension de CRUDImpl
    @Override
    public Category create(Category category) throws Exception {
        return repo.save(category);


    @Override
    public Category update(Category category) throws Exception {
        return repo.save(category);
    }

    @Override
    public List<Category> readAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Category readById(Integer id) throws Exception {
        //si no lo encuentra envia un null
        return repo.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }

     */


}
