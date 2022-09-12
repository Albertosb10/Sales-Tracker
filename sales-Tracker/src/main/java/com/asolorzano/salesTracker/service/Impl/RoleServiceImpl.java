package com.asolorzano.salesTracker.service.Impl;

import com.asolorzano.salesTracker.model.Category;
import com.asolorzano.salesTracker.model.Role;
import com.asolorzano.salesTracker.repository.IGenericRepository;
import com.asolorzano.salesTracker.repository.IRoleRepository;
import com.asolorzano.salesTracker.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends CRUDImpl<Role, Integer> implements IRoleService {

    @Autowired
    private IRoleRepository repo;

    @Override
    protected IGenericRepository<Role, Integer> getRepo() {

        return repo;
    }

    @Override
    public List<Category> findByNameLike(String name) {
        return null;
    }
}

