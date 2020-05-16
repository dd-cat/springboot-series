package com.example.coolq.exception.base;

public class BaseBadRequestException extends BaseException {

    public BaseBadRequestException(int code, String msg) {
        super(code, msg);
    }

}
