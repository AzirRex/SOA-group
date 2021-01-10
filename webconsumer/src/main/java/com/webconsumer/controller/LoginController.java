package com.webconsumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

@Controller
@DefaultProperties(defaultFallback = "notFound")
public class LoginController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/data")
    @ResponseBody
    public String data()
    {
        return "data";
    }



    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"),//时间超时降级
            @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"), //是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" ,value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" ,value = "60"),//失败率达到多少之后开启熔断自动降级
    })
    @RequestMapping("/loginNow")
    public String loginCheck(
            int id,
            String password,
            HttpSession httpSession
    )
    {
//        try{
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }



        String permission =restTemplate.getForObject("http://GATEWAY-CLIENT/USERPROVIDER/login/check/"+id+"/"+password,String.class);
        String username=restTemplate.getForObject("http://GATEWAY-CLIENT/USERPROVIDER/api/user/"+id,String.class);
        httpSession.setAttribute("userId",id);
        httpSession.setAttribute("username",username);
        if(permission.equals("true")) return "index";
        return "login";


    }


    @RequestMapping("/404")
    public String notFound()
    {
        return "404";
    }



}
