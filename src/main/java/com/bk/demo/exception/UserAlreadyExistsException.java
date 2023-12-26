package com.bk.demo.exception;

public class UserAlreadyExistsException extends RuntimeException {
    private String message;

    public UserAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
//hii there
    public UserAlreadyExistsException() {
    }
}