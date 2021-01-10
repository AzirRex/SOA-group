package com.example.lostandfoundprovider.dao;

import com.example.lostandfoundprovider.Entity.LostAndFound;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
//@Repository
public interface LostAndFoundDao {

    List<LostAndFound> lostAndFoundList();

    @Select("select * from lost where lost_ID=#{id}")
    LostAndFound getLosById(Integer id);

    @Select("select * from lost")
    List<LostAndFound> getAll();

    //http://localhost:8080/addlost?user_id=1&user_name=123&address=456&getTime=123&remark=123&type=0
    @Insert("insert into lost(userId,name,location,time,information,type) values(#{userId},#{name},#{location},#{time},#{information},#{type})")
    boolean addlost(Integer userId,String name,String location,String time,String information,Integer type);

    @Delete("delete from lost where lost_ID= #{lost_ID}")
    boolean DeleteByLostId(Integer lost_ID);
}
