package com.example.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.domain.User;
import com.example.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 添加
     *
     * @param user
     * @return
     */
    @GetMapping("save")
    public String save(User user) {
        userService.save(user);
        return "success";
    }

    /**
     * 根据id主键删除
     *
     * @param id
     * @return
     */
    @GetMapping("del")
    public String del(String id) {
        userService.removeById(id);
        return "success";
    }

    @GetMapping("list")
    public List<User> list(String id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //设置查询条件
        queryWrapper.eq("id", id);
        return userService.list(queryWrapper);
    }

    @GetMapping("update")
    public String update(User user) {
        userService.updateById(user);
        return "success";
    }
}
