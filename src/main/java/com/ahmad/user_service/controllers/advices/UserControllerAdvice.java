package com.ahmad.user_service.controllers.advices;

import com.ahmad.user_service.dtos.ExceptionDto;
import com.ahmad.user_service.exceptions.IncorrectUsernamePasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(IncorrectUsernamePasswordException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    private ExceptionDto handleIncorrectUsernamePasswordException(IncorrectUsernamePasswordException ex){
        ExceptionDto exDto = new ExceptionDto();
        exDto.setMessage(ex.getMessage());
        exDto.setStatus("Failure");
        return exDto;
    }
}
