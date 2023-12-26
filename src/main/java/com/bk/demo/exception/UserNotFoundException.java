package com.bk.demo.exception;

public class UserNotFoundException extends RuntimeException {
    private String message;
//Constructor for UserNotFoundException
    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }
//Hi GitHub
    //hello Git 1
    public UserNotFoundException() {
    }
}