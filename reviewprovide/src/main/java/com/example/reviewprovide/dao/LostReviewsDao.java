package com.example.reviewprovide.dao;


import com.example.reviewprovide.entities.LostReviews;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
//@Repository
public interface LostReviewsDao {

    @Insert("insert into lost_reviews(userID,time,information,userName,lost_ID) values(#{userID},#{time},#{information},#{userName},#{lost_ID})")
    boolean addReviews(Integer userID,String time,String information,String userName,Integer lost_ID);

    @Select("select * from lost_reviews where lost_ID=#{lost_id}")
    List<LostReviews> getReviews(Integer lost_id);

    @Delete("delete from lost_reviews where lost_ID=#{lost_id}")
    boolean deleteReviews(Integer lost_id);
}

