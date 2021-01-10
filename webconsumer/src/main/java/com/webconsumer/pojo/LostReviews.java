package com.webconsumer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LostReviews {

    private Integer reviewsID;
    private Integer userID;
    private String location;
    private String time;
    private String information;
    private String userName;
    private Integer lost_ID;
}
