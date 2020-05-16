package com.example.coolq.exception;

import com.example.coolq.exception.base.BaseServerErrorException;

public class CoolqRequestException extends BaseServerErrorException {

    public CoolqRequestException() { super(5000, "Coolq request error"); }

}
