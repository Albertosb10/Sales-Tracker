package com.asolorzano.salesTracker.service.Impl;

import com.asolorzano.salesTracker.model.Category;
import com.asolorzano.salesTracker.model.Product;
import com.asolorzano.salesTracker.repository.IGenericRepository;
import com.asolorzano.salesTracker.repository.IProductRepository;
import com.asolorzano.salesTracker.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl  extends CRUDImpl<Product, Integer> implements IProductService {

    @Autowired
    private IProductRepository repo;
    @Override
    protected IGenericRepository<Product, Integer> getRepo() {
        return repo;
    }

    /*@Override
    public List<Category> findByNameLike(String name) {
        return null;
    }

     */


}
