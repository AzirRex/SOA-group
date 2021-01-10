package com.example.lostandfoundprovider.service;


import com.example.lostandfoundprovider.Entity.LostAndFound;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface LostAndFoundService {

    LostAndFound getLosById(Integer id);


    List<LostAndFound> getAll();


    boolean addlost(Integer userId,String name,String location,String time,String information,Integer type);

    boolean DeleteByLostId(Integer lost_ID);

}
