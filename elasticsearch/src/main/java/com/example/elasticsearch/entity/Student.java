package com.example.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Author: dd-cat
 * @CreateTime: 2020-06-20 22:43
 * @Description:
 */
@Document(indexName = "stu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    private Integer id;
    @Field(type = FieldType.Keyword)
    private String firstName;
    @Field(type = FieldType.Keyword)
    private String secondName;
    @Field(type = FieldType.Integer)
    private Integer age;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String introduce;

}
