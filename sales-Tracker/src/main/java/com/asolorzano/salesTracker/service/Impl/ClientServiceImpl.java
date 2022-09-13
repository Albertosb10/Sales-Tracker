package com.asolorzano.salesTracker.service.Impl;

import com.asolorzano.salesTracker.model.Category;
import com.asolorzano.salesTracker.model.Client;
import com.asolorzano.salesTracker.repository.IClientRepository;
import com.asolorzano.salesTracker.repository.IGenericRepository;
import com.asolorzano.salesTracker.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
    public class ClientServiceImpl extends CRUDImpl<Client, Integer> implements IClientService {
        @Autowired
        private IClientRepository repo;

        @Override
        protected IGenericRepository<Client, Integer> getRepo() {
            return repo;
        }

    /*
    @Override

    public List<Category> findByNameLike(String name) {
        return null;

     */
    }




