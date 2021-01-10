package com.example.reviewprovide.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LostReviews {

    private Integer reviewsID;
    private Integer userID;
    private String location;
    private String time;
    private String information;
    private String userName;
    private Integer lost_ID;
}
