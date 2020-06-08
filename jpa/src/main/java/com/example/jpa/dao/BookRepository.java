package com.example.jpa.dao;

import com.example.jpa.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * @Author: 梦中梦i
 * @CreateTime: 2020-06-06 11:03
 * @Description:
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    //rel 表示接口查询中，这个方法的 key

    /**
     * rel 表示接口查询中，这个方法的 key
     * path 表示请求路径
     */
    @RestResource(rel = "byauthor", path = "byauthor")
    List<Book> findBookByAuthorContaining(@Param("author") String author);
}
