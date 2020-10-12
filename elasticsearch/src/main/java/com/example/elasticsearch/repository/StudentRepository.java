package com.example.elasticsearch.repository;

import com.example.elasticsearch.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: 梦中梦i
 * @CreateTime: 2020-06-20 22:46
 * @Description:
 */
public interface StudentRepository extends ElasticsearchRepository<Student,Long> {
    /**
     *
     * @param firstName
     * @param pageable
     * @return
     */
    Page<Student> findByFirstName(String firstName, Pageable pageable);

    /**
     *
     * @param content
     * @param pageable
     * @return
     */
    Page<Student> findByIntroduceLike(String content,Pageable pageable);
}
