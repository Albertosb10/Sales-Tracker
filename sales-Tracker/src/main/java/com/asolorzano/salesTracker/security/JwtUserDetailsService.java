package com.asolorzano.salesTracker.security;


import com.asolorzano.salesTracker.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//2
@Service
public class JwtUserDetailsService implements UserDetailsService {


    @Autowired
    private IUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.asolorzano.salesTracker.model.User userData = repository.findOneByUsername(username);
        if(userData==null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        List<GrantedAuthority> roles = new ArrayList<>();

        String role = userData.getRole().getName();
        System.out.println("Role: " +role);
        roles.add(new SimpleGrantedAuthority(role));

        //Import User class from Spring
        return new User(userData.getUsername(), userData.getPassword(), userData.isEnabled(), true, true, true, roles);


    }

}
