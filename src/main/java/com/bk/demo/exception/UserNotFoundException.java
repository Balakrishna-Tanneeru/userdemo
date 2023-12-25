package com.bk.demo.exception;

public class UserNotFoundException extends RuntimeException {
    private String message;

    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }
//user not found exception.
    public UserNotFoundException() {
    }
}