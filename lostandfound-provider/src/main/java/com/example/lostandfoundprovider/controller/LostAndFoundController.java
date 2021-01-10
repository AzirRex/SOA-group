package com.example.lostandfoundprovider.controller;


import com.example.lostandfoundprovider.Entity.LostAndFound;
import com.example.lostandfoundprovider.service.IMessage;
import com.example.lostandfoundprovider.service.implement.LostAndFoundServiceImp;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.IPAddress;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

//全局降级操作up
@Api(tags = "失物招领API管理")
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "global_Fallback")
public class LostAndFoundController {
    @Resource
    private LostAndFoundServiceImp lostAndFoundServiceImp;
    @Resource
    private IMessage iMessage;




    //查询数据库中已有的该ID信息
    @ApiOperation("失物详情")
    @GetMapping("/lostandfound/{lost_ID}")
    @ResponseBody
    public LostAndFound getLost(@ApiParam("失物ID") @PathVariable("lost_ID") Integer lost_ID){
        LocalDateTime time = LocalDateTime.now();
        String msg = time.toString()+"-"+"select-lostId="+lost_ID+"-lostandfound" ;
        iMessage.send(msg);
        return lostAndFoundServiceImp.getLosById(lost_ID);
    }

    //查询数据库中所有信息
    @ApiOperation("失物列表")
    @GetMapping("/lostandfound")
    @ResponseBody
    public List<LostAndFound> getLostAll(){
        List<LostAndFound> lostAndFounds = lostAndFoundServiceImp.getAll();
        LocalDateTime time = LocalDateTime.now();
        String msg = time.toString()+"-"+"select-all-lostandfound" ;
        iMessage.send(msg);
        return lostAndFoundServiceImp.getAll();
    }

    //插入失物招领
    @ApiOperation("发布失物招领请求")
    @GetMapping("/lostandfound/add")
    @ResponseBody
    public String addLost(@ApiParam("用户ID") @RequestParam(name = "user_id") Integer user_id,
                          @ApiParam("物品名称") @RequestParam(name = "name") String name,
                          @ApiParam("拾取地点") @RequestParam(name = "address") String address,
                          @ApiParam("拾取时间") @RequestParam(name = "getTime") String getTime,
                          @ApiParam("物品描述") @RequestParam(name = "information") String information,
                          @ApiParam("类型") @RequestParam(name = "type") Integer type){
        lostAndFoundServiceImp.addlost(user_id, name, address, getTime, information, type);
        LocalDateTime time = LocalDateTime.now();
        String msg = time.toString()+"-"+"insert-userId="+user_id+"-lostandfound" ;
        iMessage.send(msg);
        return(user_id.toString()+name+address+getTime+information+type.toString());
    }

    //查询数据库中相应ID的信息
    @ApiOperation("失物删除")
    @RequestMapping(value = "/lostandfound/{lost_ID}", method = RequestMethod.DELETE)
    @ResponseBody
    public Boolean deleteLost(@ApiParam("失物ID") @PathVariable("lost_ID") Integer lost_ID){
        LocalDateTime time = LocalDateTime.now();
        String msg = time.toString()+"-"+"delete-lostId="+lost_ID+"-lostandfound" ;
        iMessage.send(msg);
        return lostAndFoundServiceImp.DeleteByLostId(lost_ID);
    }
}
