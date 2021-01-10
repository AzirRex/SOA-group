package com.example.applyprovide.service;



import com.example.applyprovide.entities.LostApply;

import java.util.List;

public interface LostApplyService {

    List<LostApply> getAppById(Integer user_id);


    boolean addLostApply(String name,String e_mail,String QQ,String phone,Integer user_id,String message);

    List<LostApply> getAppByGetId(Integer getter_id);
    boolean DeleteByLostId(Integer lost_ID);
    boolean DeleteByBpplyId(Integer apply_id);
    public boolean addLostApply(String name, String e_mail, String QQ, String phone, Integer user_id, String message,Integer getter_id,Integer lost_ID);
}