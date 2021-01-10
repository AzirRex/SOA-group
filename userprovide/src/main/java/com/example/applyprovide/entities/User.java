package com.example.applyprovide.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class User implements Serializable {

    private int userId;
    private String username;
    private String password;



}
