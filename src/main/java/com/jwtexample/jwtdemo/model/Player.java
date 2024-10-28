package com.jwtexample.jwtdemo.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data

@Entity
public class Player {

    private Long id;
    private String name;
    private int goals;

}
