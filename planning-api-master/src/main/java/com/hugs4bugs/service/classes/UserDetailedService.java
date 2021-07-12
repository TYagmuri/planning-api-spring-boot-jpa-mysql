package com.hugs4bugs.service.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailedService implements UserDetailsService {

    private final UsersServiceImpl service;

    @Autowired
    public UserDetailedService(UsersServiceImpl service) {
        this.service = service;
    }


    @Override
    public UserDetails loadUserByUsername(String code) throws UsernameNotFoundException {
        String password = service.getUserByCode(code).getPassword();
        return new User(code, password, new ArrayList<>());
    }
}
