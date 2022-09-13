package com.asolorzano.salesTracker.service.Impl;

import com.asolorzano.salesTracker.model.Category;
import com.asolorzano.salesTracker.model.User;
import com.asolorzano.salesTracker.repository.IGenericRepository;
import com.asolorzano.salesTracker.repository.IUserRepository;
import com.asolorzano.salesTracker.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends CRUDImpl<User, Integer> implements IUserService {

        @Autowired
        private IUserRepository repo;
        @Override
        protected IGenericRepository<User, Integer> getRepo() {
            return repo;
        }


}

