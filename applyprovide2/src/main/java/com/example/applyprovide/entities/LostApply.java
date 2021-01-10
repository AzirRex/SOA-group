package com.example.applyprovide.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LostApply {

    private Integer apply_id;
    private String name;
    private String e_mail;
    private String QQ;
    private String phone;
    private Integer user_id;
    private String message;
    private Integer getter_id;
    private Integer lost_ID;
}
