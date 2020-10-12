package com.example.jpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author: dd-cat
 * @CreateTime: 2020-06-06 10:57
 * @Description:
 */
@Entity(name = "t_book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "book_name")
    private String name;
    private String author;

}
