package com.webconsumer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Forum {
    private int post_id;
    private int user_id;
    private Long time;
    private String content;
    private String type;

}
