package com.ahmad.userservice.exceptions;

public class IncorrectUsernamePasswordException extends RuntimeException {
    public IncorrectUsernamePasswordException(String message){
        super(message);
    }
}
