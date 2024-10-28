package com.jwtexample.jwtdemo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name="usr_tbl")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
