package com.example.mybatis.service;

import com.example.mybatis.entity.Test1;
import com.example.mybatis.entity.Test2;
import com.example.mybatis.mapper.test1.Test1Mapper;
import com.example.mybatis.mapper.test2.Test2Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    private static final Logger log = LoggerFactory.getLogger(TestService.class);

    @Autowired
    private Test1Mapper test1Mapper;

    @Autowired
    private Test2Mapper test2Mapper;

    public void getStudents() {
        List<Test1> test1 = test1Mapper.getAll();
        List<Test2> test2 = test2Mapper.getAll();

        log.info("数据源DB1:" + test1);
        log.info("数据源DB2:" + test2);
    }
}
