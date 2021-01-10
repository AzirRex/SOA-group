package com.example.reviewprovide.service.implement;



import com.example.reviewprovide.service.IMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

//调用消息中间件
@EnableBinding(Source.class)//消息推送管道
public class MessageProvider implements IMessage {

    @Resource
    private MessageChannel output;//信息发送信道

    @Value("${server.port}")
    private String serverPort;

    @Value("${eureka.instance.ip-address}")
    private String ipAddress;

    @Override
    public String send(String msg) {
        String _msg = msg+'-'+this.ipAddress+':'+this.serverPort;
        System.out.println(_msg);
        output.send(MessageBuilder.withPayload(_msg).build());
        return null;
    }
}
