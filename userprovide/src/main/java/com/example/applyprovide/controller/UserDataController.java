package com.example.applyprovide.controller;



import com.example.applyprovide.service.IMessage;
import com.example.applyprovide.service.UserDataService;
import com.example.applyprovide.service.implement.UserDataServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;

//全局降级操作up
@Api(tags = "用户登录注册管理")
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "global_Fallback")
public class UserDataController {

    @Resource
    UserDataServiceImpl userDataService;
    @Resource
    private IMessage iMessage;
    @GetMapping("/login/check/{id}/{password}")
    public String checkUser(
            @ApiParam("用户ID") @PathVariable int id,
            @ApiParam("用户密码") @PathVariable String password
    )
    {
        System.out.println(id);
        System.out.println(password);
        String password_db=userDataService.getPasswordById(id);
        LocalDateTime time = LocalDateTime.now();
        String msg = time.toString()+"-"+"select-userId="+id+"-user" ;
        iMessage.send(msg);
        if(password.equals(password_db)) return "true";
        return "false";

    }

    @GetMapping("/api/user/{id}")
    public String getUsernameById( @ApiParam("用户ID") @PathVariable int id)
    {
        String username=userDataService.getUsernameById(id);
        LocalDateTime time = LocalDateTime.now();
        String msg = time.toString()+"-"+"select-userId="+id+"-user" ;
        iMessage.send(msg);
        return username;
    }
}
