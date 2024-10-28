package com.jwtexample.jwtdemo.controller;

import com.jwtexample.jwtdemo.model.User;
import com.jwtexample.jwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public User register(@RequestBody User user){
        return userService.signup(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody User user){
        return userService.authenticate(user);

    }
}
