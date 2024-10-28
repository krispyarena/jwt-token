package com.jwtexample.jwtdemo.repository;

import com.jwtexample.jwtdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
