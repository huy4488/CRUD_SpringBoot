package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

import java.sql.Time;
import java.util.Calendar;
import java.util.*;

public class ResponseBodyException {
    private String messeage;
    private HttpStatus status;
    private Calendar time;

    public ResponseBodyException(String messeage, HttpStatus status, Calendar time) {
        this.messeage = messeage;
        this.status = status;
        this.time = time;
    }

    public String getMesseage() {
        return messeage;
    }

    public void setMesseage(String messeage) {
        this.messeage = messeage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }
}
