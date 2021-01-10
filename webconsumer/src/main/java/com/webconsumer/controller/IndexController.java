package com.webconsumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@DefaultProperties(defaultFallback = "notFound")
public class IndexController {


    @RequestMapping("/")
    public String Login(HttpSession httpSession)
    {
        if(httpSession.getAttribute("userId")==null) return "login";
        return "index";
    }

}
