package com.example.coolq.vo;

import com.example.coolq.exception.base.BaseException;
import lombok.Data;

@Data
public class ErrorVO {

    private Integer code;
    private String msg;

    public ErrorVO(BaseException e) {
        this.code = e.getCode();
        this.msg = e.getMsg();
    }

}
