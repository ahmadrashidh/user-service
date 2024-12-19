package com.ahmad.userservice.controllers;

import com.ahmad.userservice.dtos.*;
import com.ahmad.userservice.models.Token;
import com.ahmad.userservice.models.User;
import com.ahmad.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    private UserService authService;


    public UserController(UserService authService){
        this.authService = authService;
    }

    @PostMapping("signup")
    public SignupResponseDto signup(@RequestBody SignupRequestDto signupReqDto){
        SignupDto signupDto = this.mapSignupRequestDtoToSignupDto(signupReqDto);
        return this.mapUserToSignupResponseDto(this.authService.signup(signupDto));
    }

    @PostMapping("login")
    public Token login(@RequestBody LoginRequestDto loginReqDto){

        return this.authService.login(loginReqDto.getEmailAddress(), loginReqDto.getPassword());

    }

    @PostMapping("logout")
    public ResponseEntity<Void> logout(@RequestBody TokenDto tokenDto){

        this.authService.logout(tokenDto.getToken());

        return ResponseEntity.ok().build();
    }

    private SignupResponseDto mapUserToSignupResponseDto(User user){
        if(user == null)
            return null;

        SignupResponseDto signupResponseDto = new SignupResponseDto();
        signupResponseDto.setName(user.getName());
        signupResponseDto.setEmailAddress(user.getEmailAddress());
        return signupResponseDto;
    }

    private SignupDto mapSignupRequestDtoToSignupDto(SignupRequestDto signupRequestDto){
        if(signupRequestDto == null)
            return null;

        SignupDto signupDto = new SignupDto();
        signupDto.setName(signupRequestDto.getName());
        signupDto.setEmailAddress(signupRequestDto.getEmailAddress());
        signupDto.setPassword(signupRequestDto.getPassword());
        return signupDto;
    }
}
