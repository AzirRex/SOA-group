package com.example.applyprovide.dao;


import com.example.applyprovide.entities.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogDao {

    @Select("select * from run_log")
    public List<Log> getAll();

    @Insert("insert into run_log (log_information) values(#{log_information})")
    public Boolean InsertLog(String log_information);
}
