package com.jam.pocket.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jam on 2017/1/8.
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(Exception e){
        Map<String, Object> responseBody = new HashMap<String, Object>();
        responseBody.put("message", e.getMessage());
        responseBody.put("exception", e.getClass().getSimpleName());
        return new ResponseEntity<Object>(responseBody,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
