package com.example.demo.config;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.exceptions.ResponseBodyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Calendar;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ResponseBodyException> handleConflict(ResourceNotFoundException ex){
        ResponseBodyException bodyException = new ResponseBodyException(ex.getMessage(),HttpStatus.NOT_FOUND, Calendar.getInstance());
        return new ResponseEntity<>(bodyException,HttpStatus.NOT_FOUND);
    }




}
