package com.example.applyprovide.service.implement;

import com.example.applyprovide.dao.LogDao;
import com.example.applyprovide.entities.Log;
import com.example.applyprovide.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Resource
    LogDao logDao;


    @Override
    public List<Log> getAll() {
        return logDao.getAll();
    }

    @Override
    public Boolean InsertLog(String log_information) {
        return logDao.InsertLog(log_information);
    }

}
