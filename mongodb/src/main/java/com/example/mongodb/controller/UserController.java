package com.example.mongodb.controller;

import com.example.mongodb.entity.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 梦中梦i
 * @CreateTime: 2020-06-08 22:19
 * @Description:
 */
@RestController
public class UserController {

    @Resource
    private MongoTemplate mongoTemplate;

    // 添加用户
    @GetMapping("save")
    public void insertUser(User user) {
        mongoTemplate.save(user);
    }

    // 根据id获取用户
    @GetMapping("select/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, User.class);
    }


    // 更新用户
    @GetMapping("update")
    public void updateUser(User user) {
        Query query = new Query(Criteria.where("id").is(user.getId()));

        Update update = new Update();
        update.set("name", user.getName());
        update.set("sex", user.getSex());

        mongoTemplate.updateFirst(query, update, User.class);
    }

    // 删除用户
    @GetMapping("delete")
    public void deleteUser(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, User.class);
    }
}
