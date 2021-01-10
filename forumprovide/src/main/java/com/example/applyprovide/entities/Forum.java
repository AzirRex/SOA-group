package com.example.applyprovide.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Forum {
    private Integer post_id;
    private Integer user_id;
    private Long time;
    private String content;
    private String type;

}
