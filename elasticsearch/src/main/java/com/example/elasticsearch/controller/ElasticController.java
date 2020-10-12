package com.example.elasticsearch.controller;

import com.example.elasticsearch.entity.Student;
import com.example.elasticsearch.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 梦中梦i
 * @CreateTime: 2020-06-20 22:47
 * @Description:
 */
@RestController
@RequestMapping("/es")
public class ElasticController {

    @Autowired
    private StudentRepository repository;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public Map<String, String> init() {
        Map<String, String> map = new HashMap<>();
        repository.save(new Student(0, "郑三", "先生", 27, "宁波工程学院20xx届学生"));
        repository.save(new Student(2, "王三", "女士", 27, "台州学院20xx届学生"));
        repository.save(new Student(10, "李四", "女士", 27, "台州学院20xx届学生"));
        map.put("code", "200");
        return map;
    }

    @RequestMapping(value = "/stuGet/{name}", method = RequestMethod.GET)
    public Map<String, Object> stuGet(@PathVariable String name) {
        Map<String, Object> map = new HashMap<>();
        Pageable pageable = PageRequest.of(0, 10);
        Page<Student> stus = repository.findByFirstName(name, pageable);
        List<Student> content = stus.getContent();
        map.put("stus", content);
        map.put("code", "200");
        List<Student> content1 = repository.findByIntroduceLike(name, pageable).getContent();
        map.put("stus2", content1);
        return map;
    }
}
