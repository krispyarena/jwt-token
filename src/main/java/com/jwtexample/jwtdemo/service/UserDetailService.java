package com.jwtexample.jwtdemo.service;

import com.jwtexample.jwtdemo.model.User;
import com.jwtexample.jwtdemo.model.UserModel;
import com.jwtexample.jwtdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("user not found");
        }
        return new UserModel(user);

    }
}
