package com.example.applyprovide.service.implement;


import com.example.applyprovide.dao.UserDao;
import com.example.applyprovide.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDataServiceImpl implements UserDataService {

    @Resource
    UserDao userMapper;


    @Override
    public String getPasswordById(int Id) {
        return userMapper.getPasswordById(Id);
    }

    @Override
    public String getUsernameById(int Id) {
        return userMapper.getUsernameById(Id);
    }


}
