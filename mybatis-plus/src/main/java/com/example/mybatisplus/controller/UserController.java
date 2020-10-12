package com.example.mybatisplus.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.entity.User;
import com.example.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户列表
     *
     * @param page 分页
     * @param user 用户
     * @return Page<User>
     */
    @GetMapping("list")
    public Page<User> list(Page page, User user) {
        return userService.page(page, Wrappers.query(user));
    }

    /**
     * 添加
     *
     * @param user 用户
     */
    @PostMapping("save")
    public void save(User user) {
        userService.save(user);
    }

    /**
     * 删除
     *
     * @param id 用户id
     */
    @DeleteMapping("del")
    public void del(String id) {
        userService.removeById(id);
    }

    /**
     * 修改
     *
     * @param user 用户
     */
    @PutMapping("update")
    public String update(User user) {
        userService.updateById(user);
        return "success";
    }
}
