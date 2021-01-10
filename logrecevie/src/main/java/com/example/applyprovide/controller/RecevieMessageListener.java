package com.example.applyprovide.controller;

import com.example.applyprovide.service.implement.LogServiceImpl;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@EnableBinding(Sink.class)
public class RecevieMessageListener {
    @Resource
    private LogServiceImpl logService;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message)
    {
        String getMsg=message.getPayload();
        //System.out.println("日志中心接收："+getMsg );
        logService.InsertLog(getMsg);

    }
}
