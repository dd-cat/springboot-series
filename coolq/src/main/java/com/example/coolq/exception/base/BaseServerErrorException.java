package com.example.coolq.exception.base;

public class BaseServerErrorException extends BaseException {

    public BaseServerErrorException(int code, String msg) {
        super(code, msg);
    }

}
