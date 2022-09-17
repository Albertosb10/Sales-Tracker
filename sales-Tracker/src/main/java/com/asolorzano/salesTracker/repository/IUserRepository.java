package com.asolorzano.salesTracker.repository;

import com.asolorzano.salesTracker.model.User;

public interface IUserRepository extends  IGenericRepository<User, Integer>{

    User findOneByUsername(String username);
}
