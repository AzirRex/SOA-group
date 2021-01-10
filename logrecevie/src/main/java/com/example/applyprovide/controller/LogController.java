package com.example.applyprovide.controller;

import com.example.applyprovide.entities.Log;
import com.example.applyprovide.entities.LogOut;
import com.example.applyprovide.service.LogService;
import com.example.applyprovide.service.implement.LogServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class LogController {
    @Resource
    private LogServiceImpl logService;


    @RequestMapping("/log")
    public ModelAndView getall() {
        List<Log> logList = logService.getAll();
        List<LogOut> logOuts = new ArrayList<LogOut>();
        for(int i=0;i<logList.size();i++)
        {
            String[] getlist= logList.get(i).getLog_information().split("-");
            LogOut logOut=new LogOut();
            logOut.log_id=logList.get(i).getLog_id();
            logOut.time=getlist[0]+getlist[1]+getlist[2];
            logOut.type=getlist[3];
            logOut.caozuo_shuxing=getlist[4];
            logOut.caozuo_table=getlist[5];
            logOut.caozuo_ip=getlist[6];
            logOuts.add(logOut);
        }

        ModelAndView mav = new ModelAndView();

        mav.setViewName("log.html");

        mav.addObject("logList",logOuts);
        return mav;
    }
}
