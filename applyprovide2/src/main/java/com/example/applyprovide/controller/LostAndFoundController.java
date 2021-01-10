package com.example.applyprovide.controller;


import com.example.applyprovide.entities.LostApply;
import com.example.applyprovide.service.IMessage;
import com.example.applyprovide.service.implement.LostApplyServiceImp;
import com.example.applyprovide.service.implement.MessageProvider;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

//全局降级操作up
@Api(tags = "申请API管理")
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "global_Fallback")
public class LostAndFoundController {

    @Autowired
    RestTemplate restTemplate;

    @Resource
    private LostApplyServiceImp lostApplyServiceImp;
    @Resource
    private IMessage iMessage;

    //添加申请
    @ApiOperation("添加申请")
    @GetMapping("/lostandfound/apply/add")
    @ResponseBody
    public boolean addlostapply( @ApiParam("申请者姓名") @RequestParam(name = "name") String name,
                                 @ApiParam("e-mail") @RequestParam(name = "e_mail") String e_mail,
                                 @ApiParam("QQ") @RequestParam(name = "QQ") String QQ,
                                 @ApiParam("phoneNumber") @RequestParam(name = "phone") String phone,
                                 @ApiParam("user_id") @RequestParam(name = "user_id") Integer user_id,
                                 @ApiParam("申请简介") @RequestParam(name = "message") String message,
                                 @ApiParam("拾取者ID") @RequestParam(name = "getter_id") Integer getter_id,
                                 @ApiParam("失物ID") @RequestParam(name = "lost_ID") Integer lost_ID ) {
        LocalDateTime Time = LocalDateTime.now();
        String msg = Time.toString()+"-"+"insert-user_id="+user_id+"-lost_apply" ;
        iMessage.send(msg);
        return lostApplyServiceImp.addLostApply(name,e_mail,QQ,phone,user_id,message,getter_id,lost_ID);
    }

    //读取所有申请
    @ApiOperation("读取用户申请")
    @GetMapping("/lostandfound/applytest")
    @ResponseBody
    public List<LostApply> getLostapply(@ApiParam("用户ID") @RequestParam(name = "user_id") Integer user_id) {
        LocalDateTime Time = LocalDateTime.now();
        String msg = Time.toString()+"-"+"select-user_id="+user_id+"-lost_apply" ;
        iMessage.send(msg);
        return lostApplyServiceImp.getAppById(user_id);
    }

    //读取所有相应用户的申请
    @ApiOperation("读取用户申请")
    @RequestMapping("/lostandfound/apply")
    @ResponseBody
    public List<LostApply> getTableByGetter(@ApiParam("拾取者ID") @RequestParam(name = "getter_id") Integer getter_id)
    {
        LocalDateTime Time = LocalDateTime.now();
        String msg = Time.toString()+"-"+"select-getter_id="+getter_id+"-lost_apply" ;
        iMessage.send(msg);
        return lostApplyServiceImp.getAppByGetId(getter_id);
    }


    @ApiOperation("删除相应物品的申请")
    @RequestMapping(value = "/lostandfound/applyaccess/{lost_ID}", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteApplyByLost(@ApiParam("物品ID") @PathVariable(name = "lost_ID") Integer lost_ID) {
        LocalDateTime Time = LocalDateTime.now();
        String msg = Time.toString()+"-"+"delete-lost_ID="+lost_ID+"-lost_apply" ;
        iMessage.send(msg);
        //级联删除
        restTemplate.delete("http://LOSTANDFOUND-PROVIDER/lostandfound/"+lost_ID);//删除对应的帖子
        restTemplate.delete("http://REVIEW-PROVIDER/lostandfound/review?lost_id="+lost_ID);//删除帖子的评论
        return lostApplyServiceImp.DeleteByLostId(lost_ID);
    }

    @ApiOperation("根据申请ID删除相应物品的申请")
    @RequestMapping("/lostandfound/applydenied/{apply_id}")
    @ResponseBody
    public boolean deleteApplyByApply(@ApiParam("申请ID") @PathVariable(name = "apply_id") Integer apply_id) {
        LocalDateTime Time = LocalDateTime.now();
        String msg = Time.toString()+"-"+"delete-apply_id="+apply_id+"-lost_apply" ;
        iMessage.send(msg);
        return lostApplyServiceImp.DeleteByBpplyId(apply_id);
    }
}
