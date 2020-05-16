package com.example.mybatis.mapper.test2;

import com.example.mybatis.entity.Test2;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Test2Mapper {
    @Select("select * from test2")
    List<Test2> getAll();
}
