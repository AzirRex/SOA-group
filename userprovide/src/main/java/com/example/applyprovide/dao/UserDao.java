package com.example.applyprovide.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select password from user where userId = #{Id}")
    public String getPasswordById(int Id);

    @Select("select username from user where userId=#{Id}")
    public String getUsernameById(int Id);
}
