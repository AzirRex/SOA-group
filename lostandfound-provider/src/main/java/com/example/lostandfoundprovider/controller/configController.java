package com.example.lostandfoundprovider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//读取配置文件的Controller
@RestController
@RefreshScope
public class configController {
    @Value("${server.port}")
    private String serverPort;

    @Value( ".config.info" )
    private String configInfo;

    @GetMapping("/configInfo")
    public String configInfo()
    {
        return "serverPort: "+serverPort+"\t\n\n" +
                "configInfo: " +configInfo;
    }
}
