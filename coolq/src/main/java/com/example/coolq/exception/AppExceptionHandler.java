package com.example.coolq.exception;

import com.example.coolq.exception.base.BaseBadRequestException;
import com.example.coolq.exception.base.BaseNotFoundException;
import com.example.coolq.exception.base.BaseServerErrorException;
import com.example.coolq.exception.base.BaseUnauthorizedException;
import com.example.coolq.vo.ErrorVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = BaseServerErrorException.class)
    @ResponseBody
    public ResponseEntity<ErrorVO> serverErrorHandler(HttpServletRequest req, BaseServerErrorException e)
            throws BaseServerErrorException {
        return new ResponseEntity<>(new ErrorVO(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = BaseBadRequestException.class)
    @ResponseBody
    public ResponseEntity<ErrorVO> badRequestHandler(HttpServletRequest req, BaseBadRequestException e)
            throws BaseBadRequestException {
        return new ResponseEntity<>(new ErrorVO(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BaseNotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> notFoundHandler(HttpServletRequest req, BaseNotFoundException e)
            throws BaseNotFoundException {
        return new ResponseEntity<>("source not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BaseUnauthorizedException.class)
    @ResponseBody
    public ResponseEntity<String> notFoundHandler(HttpServletRequest req, BaseUnauthorizedException e)
            throws BaseUnauthorizedException {
        return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<String> othersErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        e.printStackTrace();
        return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
