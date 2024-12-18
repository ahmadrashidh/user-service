package com.ahmad.userservice.controllers;

import com.ahmad.userservice.dtos.LoginRequestDto;
import com.ahmad.userservice.dtos.TokenDto;
import com.ahmad.userservice.dtos.SignupRequestDto;
import com.ahmad.userservice.models.Token;
import com.ahmad.userservice.models.User;
import com.ahmad.userservice.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    private AuthenticationService authService;

    public UserController(AuthenticationService authService){
        this.authService = authService;
    }

    @PostMapping("signup")
    public User signup(@RequestBody SignupRequestDto signupReqDto){
        return this.authService.createUser(signupReqDto);
    }

    @PostMapping("login")
    public TokenDto login(@RequestBody LoginRequestDto loginReqDto){

        return this.authService.loginUser(loginReqDto);

    }

    @PostMapping("logout")
    public ResponseEntity<Void> logout(@RequestBody TokenDto logoutReqDto){

        this.authService.invalidateToken(logoutReqDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
