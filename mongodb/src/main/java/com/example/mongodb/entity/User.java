package com.example.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author: dd-cat
 * @CreateTime: 2020-06-08 21:57
 * @Description:
 */
@Document(collection = "demo_collection")
@Data
public class User {
    @Id
    private Long id;
    private String name;
    private String sex;
}
