package com.asolorzano.salesTracker.service.Impl;

import com.asolorzano.salesTracker.model.Category;
import com.asolorzano.salesTracker.model.Provider;
import com.asolorzano.salesTracker.repository.IGenericRepository;
import com.asolorzano.salesTracker.repository.IProviderRepository;
import com.asolorzano.salesTracker.service.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceImpl extends CRUDImpl<Provider, Integer> implements IProviderService {
    @Autowired
    private IProviderRepository repo;
    @Override
    protected IGenericRepository<Provider, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Category> findByNameLike(String name) {
        return null;
    }
}