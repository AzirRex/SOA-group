package com.webconsumer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {

    private int userId;
    private String username;
    private String password;



}
