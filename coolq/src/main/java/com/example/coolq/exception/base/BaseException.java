package com.example.coolq.exception.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends Exception {

    private int code;
    private String msg;

    BaseException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
