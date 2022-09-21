package com.asolorzano.salesTracker.service.Impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SecurityService {

    public boolean hasPermission(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String rol = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining());
        System.out.println("Username: "+username);
        return true;
    }
}
