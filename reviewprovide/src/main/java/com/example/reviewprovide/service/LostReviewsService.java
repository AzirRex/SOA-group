package com.example.reviewprovide.service;



import com.example.reviewprovide.entities.LostReviews;

import java.util.List;

public interface LostReviewsService {

    boolean addReviews(Integer userID,String time,String information,String userName,Integer lost_ID);

    List<LostReviews> getReviews(Integer lost_id);

    boolean deleteReviews(Integer lost_id);
}
