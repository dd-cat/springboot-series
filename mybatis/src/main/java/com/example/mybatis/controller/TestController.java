package com.example.mybatis.controller;

import com.example.mybatis.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public void getStudents() {
        testService.getStudents();
    }
}
