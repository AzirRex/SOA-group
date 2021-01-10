package com.example.applyprovide.dao;



import com.example.applyprovide.entities.LostApply;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
//@Repository
public interface LostApplyDao {

    @Insert("insert into lost_apply(name,e_mail,QQ,phone,user_id,message,getter_id,lost_ID) values(#{name},#{e_mail},#{QQ},#{phone},#{user_id},#{message},#{getter_id},#{lost_ID})")
    boolean addLostApply(String name,String e_mail,String QQ,String phone,Integer user_id,String message,Integer getter_id,Integer lost_ID);

    @Select("select * from lost_apply where user_id=#{user_id}")
    List<LostApply> getAppById(Integer user_id);

    @Select("select * from lost_apply where getter_id=#{getter_id}")
    List<LostApply> getAppByGetId(Integer getter_id);

    @Delete("delete from lost_apply where lost_ID=#{lost_ID}")
    boolean DeleteByLostId(Integer lost_ID);

    @Delete("delete from lost_apply where apply_id=#{apply_id}")
    boolean DeleteByBpplyId(Integer apply_id);
}

