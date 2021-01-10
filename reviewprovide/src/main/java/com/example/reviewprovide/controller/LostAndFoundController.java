package com.example.reviewprovide.controller;


import com.example.reviewprovide.entities.LostReviews;
import com.example.reviewprovide.service.IMessage;
import com.example.reviewprovide.service.implement.LostReviewsServiceImp;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

//全局降级操作up
@Api(tags = "评论API管理")
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "global_Fallback")
public class LostAndFoundController {

    @Resource
    private LostReviewsServiceImp lostReviewsServiceImp;
    @Resource
    private IMessage iMessage;

    //发布评论
    @ApiOperation("发布评论")
    @GetMapping("/lostandfound/review/add")
    @ResponseBody
    public String addreview(@ApiParam("用户ID") @RequestParam(name = "userID") Integer userID,
                            @ApiParam("发布时间") @RequestParam(name = "time") String time,
                            @ApiParam("发布内容") @RequestParam(name = "information") String information,
                            @ApiParam("用户姓名") @RequestParam(name = "userName") String userName,
                            @ApiParam("拾取物ID") @RequestParam(name = "lost_ID") Integer lost_ID)
    {
        LocalDateTime Time = LocalDateTime.now();
        String msg = Time.toString()+"-"+"insert-userId="+userID+"-lost_reviews" ;
        iMessage.send(msg);
        lostReviewsServiceImp.addReviews(userID,time,information,userName,lost_ID);
        return("true");
    }

    //获得评论
    @ApiOperation("获取物品评论")
    @GetMapping("/lostandfound/review")
    @ResponseBody
    public List<LostReviews> getReviews(@ApiParam("失物ID") @RequestParam(name = "lost_id") Integer lost_id)
    {
        LocalDateTime Time = LocalDateTime.now();
        String msg = Time.toString()+"-"+"select-lost_id="+lost_id+"-lost_reviews" ;
        iMessage.send(msg);
        return lostReviewsServiceImp.getReviews(lost_id);

    }

    //获得评论
    @ApiOperation("删除物品评论")
    @RequestMapping(value = "/lostandfound/review",method = RequestMethod.DELETE)
    @ResponseBody
    public Boolean deleteReviews(@ApiParam("失物ID") @RequestParam(name = "lost_id") Integer lost_id)
    {
        LocalDateTime Time = LocalDateTime.now();
        String msg = Time.toString()+"-"+"delete-lost_id="+lost_id+"-lost_reviews" ;
        iMessage.send(msg);
        return lostReviewsServiceImp.deleteReviews(lost_id);

    }
}
