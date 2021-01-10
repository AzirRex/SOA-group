package com.example.reviewprovide.service.implement;

import com.example.reviewprovide.dao.LostReviewsDao;
import com.example.reviewprovide.entities.LostReviews;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LostReviewsServiceImp implements LostReviewsDao {
    @Resource
    LostReviewsDao lostReviewsDao;

    @Override
    public boolean addReviews(Integer userID, String time, String information, String userName, Integer lost_ID) {
        return lostReviewsDao.addReviews(userID, time, information, userName, lost_ID);
    }

    @Override
    public List<LostReviews> getReviews(Integer lost_id) {
        return lostReviewsDao.getReviews(lost_id);
    }

    @Override
    public boolean deleteReviews(Integer lost_id) {
        return lostReviewsDao.deleteReviews(lost_id);
    }
}
