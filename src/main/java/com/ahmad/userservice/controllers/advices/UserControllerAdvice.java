package com.ahmad.userservice.controllers.advices;

import com.ahmad.userservice.dtos.ExceptionDto;
import com.ahmad.userservice.exceptions.UserNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(UserNotExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    private ExceptionDto handleProductNotFoundException(UserNotExistsException ex){
        ExceptionDto exDto = new ExceptionDto();
        exDto.setMessage(ex.getMessage());
        exDto.setStatus("Failure");
        return exDto;
    }
}
