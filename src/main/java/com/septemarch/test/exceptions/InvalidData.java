package com.septemarch.test.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidData extends RuntimeException {
    private final HttpStatus status;

    public InvalidData(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage(){
        return super.getMessage();
    }
}
