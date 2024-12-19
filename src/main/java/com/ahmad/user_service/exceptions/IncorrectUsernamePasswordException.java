package com.ahmad.user_service.exceptions;

public class IncorrectUsernamePasswordException extends RuntimeException {
    public IncorrectUsernamePasswordException(String message){
        super(message);
    }
}
