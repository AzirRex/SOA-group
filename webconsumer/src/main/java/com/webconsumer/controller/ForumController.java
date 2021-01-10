package com.webconsumer.controller;


import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.webconsumer.pojo.Comment;
import com.webconsumer.pojo.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@DefaultProperties(defaultFallback = "notFound")
public class ForumController {

    @Autowired
    RestTemplate restTemplate;


    public String notFound()
    {
        return "404";
    }


    //服务熔断
    @HystrixCommand(fallbackMethod="",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"),//时间超时降级
            @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"), //是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" ,value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" ,value = "60"),//失败率达到多少之后开启熔断自动降级
    })
    @RequestMapping("/forum")
    public String init(Model model)
    {
        String url="http://GATEWAY-CLIENT/FORUMPROVIDER/forum/all";
        System.out.println(url);
        Forum[] posts=restTemplate.getForObject(url,Forum[].class);
        List<Forum> postList= Arrays.asList(posts);
        model.addAttribute("postList",postList);
        return "blank";
    }


    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"),//时间超时降级
            @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"), //是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" ,value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" ,value = "60"),//失败率达到多少之后开启熔断自动降级
    })
    @RequestMapping("/forum/{postId}")
    public String postDetail(@PathVariable int postId ,Model model)
    {

        String postUrl="http://GATEWAY-CLIENT/FORUMPROVIDER/queryPostByPostId/"+postId;
        String reviewUrl="http://GATEWAY-CLIENT/FORUMPROVIDER/queryCommentByPostId/"+postId;

        System.out.println(postUrl);


        Forum post= restTemplate.getForObject(postUrl,Forum.class);

        Comment[] comments=  restTemplate.getForObject(reviewUrl, Comment[].class);
        System.out.println(reviewUrl);
        List<Comment> commentList=Arrays.asList(comments);
        model.addAttribute("post",post);
        model.addAttribute("comments",commentList);
        return "forumDetail";
    }




    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"),//时间超时降级
            @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"), //是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" ,value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" ,value = "60"),//失败率达到多少之后开启熔断自动降级
    })
    @RequestMapping("/addComment")
    public String addComment(
                             int postId,
                             String comment,
                             HttpSession httpSession

    ){

        if(comment=="")  return "redirect:/forum/"+postId;
        if(httpSession.getAttribute("userId")==null)
        {
            return "login";
        }
        int userId= (int) httpSession.getAttribute("userId");


        String type="1";
        String status=restTemplate.getForObject("http://GATEWAY-CLIENT/FORUMPROVIDER/addComment/"+postId+"/"+userId+"/"+"/"+comment+"/1",String.class);


        return "redirect:/forum/"+postId;
    }




    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"),//时间超时降级
            @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"), //是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" ,value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" ,value = "60"),//失败率达到多少之后开启熔断自动降级
    })
    @RequestMapping("/newPost")
    public String newPost(HttpSession session)
    {
        if(session.getAttribute("userId")==null)
        {
            return "login";
        }
        return "newPost";
    }



    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"),//时间超时降级
            @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"), //是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" ,value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" ,value = "60"),//失败率达到多少之后开启熔断自动降级
    })
    @RequestMapping("/addPost")
    public String addPost(
            String comment,
            HttpSession httpSession

    ){

        if(comment=="")  return "redirect:/newPost";
        if(httpSession.getAttribute("userId")==null)
        {
            return "login";
        }
        int userId= (int) httpSession.getAttribute("userId");


        String type="1";
        String status=restTemplate.getForObject("http://GATEWAY-CLIENT/FORUMPROVIDER/addForum/"+userId+"/"+"/"+comment+"/1",String.class);


        return "redirect:/forum";
    }


    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"),//时间超时降级
            @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"), //是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" ,value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" ,value = "60"),//失败率达到多少之后开启熔断自动降级
    })
    @RequestMapping("/deleteComment")
    public String deleteComment(
            int commentId,
            int postId,
            int userId,
            HttpSession httpSession
    )
    {
        if(httpSession.getAttribute("userId")==null)
        {
            return "login";
        }

        String status=restTemplate.getForObject("http://GATEWAY-CLIENT/FORUMPROVIDER/deleteCommentByCommentId/"+commentId,String.class);
        return "redirect:/forum/"+postId;
    }



    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"),//时间超时降级
            @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"), //是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" ,value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" ,value = "60"),//失败率达到多少之后开启熔断自动降级
    })
    @RequestMapping("/deletePost")
    public String deletePost(int postId,int userId,HttpSession httpSession)
    {
        if(httpSession.getAttribute("userId")==null)
        {
            return "login";
        }
        String status=restTemplate.getForObject("http://GATEWAY-CLIENT/FORUMPROVIDER/deletePostByPostId/"+postId,String.class);
        return "redirect:/forum";
    }






}
