package com.webconsumer.pojo;

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
    private String getter_id;
    private String lost_ID;
}
