package com.example.mybatis.mapper.test1;

import com.example.mybatis.entity.Test1;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Test1Mapper {
    @Select("select * from test1")
    List<Test1> getAll();
}
