package com.example.lostandfoundprovider.controller;



import com.example.lostandfoundprovider.service.IMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class sendController {

    @Resource
    private IMessage iMessage;

    @GetMapping(value = "/sendmessage")
    public String sendMessage()
    {
        return iMessage.send("test");
    }
}
