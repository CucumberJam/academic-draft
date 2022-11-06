package com.example.academicdraft.service;

import com.example.academicdraft.entity.Log;
import com.example.academicdraft.repository.LogRepository;
import lombok.Data;
import org.apache.logging.slf4j.SLF4JLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class LogServiceImpl implements LogService{
    private final LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository){

        this.logRepository = logRepository;
    }

    @Override
    public void info(String message){
        logRepository.save(this.createLog(message));

    }

    private Log createLog(String message){
        Log log = new Log();
        log.setMessage(message);
        log.setTs(new Date());
        return log;
    }


}
