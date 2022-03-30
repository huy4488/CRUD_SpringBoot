package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SaveDataSqlException extends RuntimeException{
    public SaveDataSqlException() {
    }

    public SaveDataSqlException(String message) {
        super(message);
    }

    public SaveDataSqlException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaveDataSqlException(Throwable cause) {
        super(cause);
    }
}
