package com.example.applyprovide.service;

import com.example.applyprovide.entities.Log;

import java.util.List;

public interface LogService {
    public List<Log> getAll();
    public Boolean InsertLog(String log_information);
}
