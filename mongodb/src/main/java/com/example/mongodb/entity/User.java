package com.example.mongodb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author: 梦中梦i
 * @CreateTime: 2020-06-08 21:57
 * @Description:
 */
@Document(collection = "demo_collection")//把一个java类声明为mongodb的文档，可以通过collection参数指定这个类对应的文档
public class User {
    @Id
    private Long id;
    private String name;
    private String sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
