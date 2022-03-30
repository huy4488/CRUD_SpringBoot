package com.example.demo.config;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.exceptions.ResponseBodyException;
import com.example.demo.exceptions.SaveDataSqlException;
import com.example.demo.service.serviceImpl.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Calendar;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler
    public ModelAndView handleConflict(ResourceNotFoundException ex){
        logger.error(ex.getMessage());
        ResponseBodyException bodyException = new ResponseBodyException(ex.getMessage(),HttpStatus.NOT_FOUND, Calendar.getInstance());
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("message",ex.getMessage());
        mav.addObject("status",HttpStatus.NOT_FOUND);
        mav.addObject("error",ex.getClass());
        return mav;
    }


    @ExceptionHandler
    public ModelAndView handleConflict(SaveDataSqlException ex){
        logger.error(ex.getMessage());
        ResponseBodyException bodyException = new ResponseBodyException(ex.getMessage(),HttpStatus.NOT_FOUND, Calendar.getInstance());
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("message",ex.getMessage());
        mav.addObject("status",HttpStatus.NOT_FOUND);
        mav.addObject("error",ex.getClass());
        return mav;
    }




}
