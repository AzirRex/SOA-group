package com.webconsumer.controller;



import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.webconsumer.pojo.LostAndFound;
import com.webconsumer.pojo.LostApply;
import com.webconsumer.pojo.LostReviews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Controller
@DefaultProperties(defaultFallback = "notFound")
public class LostAndFoundController {

    @Autowired
    RestTemplate restTemplate;


    public String notFound()
    {
        return "404";
    }


    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"),//时间超时降级
            @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"), //是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" ,value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" ,value = "60"),//失败率达到多少之后开启熔断自动降级
    })
    @RequestMapping("/lostList")
    public String LostList(Model model)
    {
        String url="http://GATEWAY-CLIENT/LOSTANDFOUND-PROVIDER/lostandfound";
        LostAndFound[] lostAndFounds=restTemplate.getForObject(url,LostAndFound[].class);
//        LostAndFound[] lostAndFounds=new LostAndFound[1];
        List<LostAndFound> LFList= Arrays.asList(lostAndFounds);
        model.addAttribute("LFList",LFList);
        return "cards";
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"),//时间超时降级
            @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"), //是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" ,value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" ,value = "60"),//失败率达到多少之后开启熔断自动降级
    })
    @RequestMapping("/lost/{lost_ID}")
    public String Lost(Model model, @PathVariable  int lost_ID)
    {
        LostAndFound lostAndFound=restTemplate.getForObject("http://GATEWAY-CLIENT/LOSTANDFOUND-PROVIDER/lostandfound/"+lost_ID,LostAndFound.class);
        LostReviews[] lostReviews=restTemplate.getForObject("http://GATEWAY-CLIENT/REVIEW-PROVIDER/lostandfound/review?lost_id="+lost_ID,LostReviews[].class);
        for(int i=0;i<lostReviews.length;i++)
        {
            lostReviews[i].setTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(Long.parseLong(lostReviews[i].getTime()))));
        }

        List<LostReviews> reviews=Arrays.asList(lostReviews);
        model.addAttribute("lf",lostAndFound);
        model.addAttribute("reviews",reviews);
        return "single-lost";
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"),//时间超时降级
            @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"), //是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" ,value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" ,value = "60"),//失败率达到多少之后开启熔断自动降级
    })
    @RequestMapping("/addReview")
    public String addReview(
            String reviewComment,
            int Lost_ID,
            HttpSession httpSession

    )
    {
        String comment=reviewComment;
        if(comment=="")  return "redirect:/lost/"+Lost_ID;
        if(httpSession.getAttribute("userId")==null)
        {
            return "login";
        }
        int userId= (int) httpSession.getAttribute("userId");
        String url ="http://GATEWAY-CLIENT/REVIEW-PROVIDER/lostandfound/review/add?userID="+userId+"&time="+System.currentTimeMillis()+"&information="+comment+"&userName="+userId+"&lost_ID="+Lost_ID;
        System.out.println(url);
        boolean status=restTemplate.getForObject(url,Boolean.class);
        System.out.println(status+"!!!!!");
        return "redirect:/lost/"+Lost_ID;

    }


    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"),//时间超时降级
            @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"), //是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" ,value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" ,value = "60"),//失败率达到多少之后开启熔断自动降级
    })
    @RequestMapping("/addApplication")
    public String addApplication(
            String name,
            String QQ,
            String phone,
            String email,
            String message,
            String Lost_ID,
            String getter_ID,
            String lost_ID,
            HttpSession httpSession
    )
    {
        if(message==""||QQ=="")  return "redirect:/lost/"+Lost_ID;
        if(httpSession.getAttribute("userId")==null)
        {
            return "login";
        }
        int userId= (int) httpSession.getAttribute("userId");
        String url ="http://GATEWAY-CLIENT/APPLY-PROVIDER/lostandfound/apply/add?user_id="+userId+"&getter_id="+getter_ID+"&message="+message+"&phone="+phone+"&name="+name+"&e_mail="+email+"&QQ="+QQ+"&lost_ID="+lost_ID;
        System.out.println(url);
        boolean status=restTemplate.getForObject(url,Boolean.class);
        System.out.println(status+"!!!!!");
        return "redirect:/lost/"+Lost_ID;



    }


    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"),//时间超时降级
            @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"), //是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" ,value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" ,value = "60"),//失败率达到多少之后开启熔断自动降级
    })
    @RequestMapping("/getDataTable")
    public String dataTable(HttpSession httpSession, Model model)
    {
        if(httpSession.getAttribute("userId")==null) return "login";
        int userId= (int) httpSession.getAttribute("userId");
        String url="http://GATEWAY-CLIENT/APPLY-PROVIDER/lostandfound/apply?getter_id="+userId;
        LostApply[] lostApplies=restTemplate.getForObject(url,LostApply[].class);
        List<LostApply> applies=Arrays.asList(lostApplies);
        model.addAttribute("applies",applies);

        return "DataTables";
    }


    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"),//时间超时降级
            @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"), //是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" ,value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" ,value = "60"),//失败率达到多少之后开启熔断自动降级
    })
    @RequestMapping("/deleteApply/{apply_id}")
    public String deleteApply(@PathVariable String apply_id)
    {
        String url = "http://GATEWAY-CLIENT/APPLY-PROVIDER/lostandfound/applydenied/"+apply_id;
        System.out.println(url);
        restTemplate.delete(url);
        return "redirect:/getDataTable";
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"),//时间超时降级
            @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"), //是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" ,value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" ,value = "60"),//失败率达到多少之后开启熔断自动降级
    })
    @RequestMapping("/deleteLost/{lost_ID}")
    public String deleteLost(@PathVariable String lost_ID)
    {
        String url = "http://GATEWAY-CLIENT/APPLY-PROVIDER/lostandfound/applyaccess/"+lost_ID;
        System.out.println(url);
        restTemplate.delete(url);
        return "redirect:/getDataTable";
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"),//时间超时降级
            @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"), //是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" ,value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" ,value = "60"),//失败率达到多少之后开启熔断自动降级
    })
    @RequestMapping("/addLost")
    public String addLost(
            String user_name,
            String address,
            String getTime,
            String remark,
            HttpSession httpSession
    )
    {
        System.out.println("!!!");
        if(httpSession.getAttribute("userId")==null)
        {
            return "login";
        }
        System.out.println("!!!");

        int user_id= (int) httpSession.getAttribute("userId");
        System.out.println("!!!");

        String url = "http://GATEWAY-CLIENT/LOSTANDFOUND-PROVIDER/lostandfound/add?user_id="+user_id+"&name="+user_name+"&address="+address+"&getTime="+getTime+"&information="+remark+"&type=0";
        System.out.println(url);

        String response=restTemplate.getForObject(url,String.class);
        System.out.println("!!!");
        return "redirect:/lostList";
    }







}

