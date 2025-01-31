package com.ahmad.user_service.controllers;

import com.ahmad.user_service.dtos.*;
import com.ahmad.user_service.models.Token;
import com.ahmad.user_service.models.User;
import com.ahmad.user_service.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;


    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("signup")
    public SignupResponseDto signup(@RequestBody SignupRequestDto signupReqDto){
        SignupDto signupDto = this.mapSignupRequestDtoToSignupDto(signupReqDto);
        return this.mapUserToSignupResponseDto(this.userService.signup(signupDto));
    }

    @PostMapping("login")
    public Token login(@RequestBody LoginRequestDto loginReqDto){

        return this.userService.login(loginReqDto.getEmailAddress(), loginReqDto.getPassword());

    }

    @PostMapping("logout")
    public ResponseEntity<Void> logout(@RequestBody TokenDto tokenDto){

        this.userService.logout(tokenDto.getToken());

        return ResponseEntity.ok().build();
    }

    @PostMapping("validate/{token}")
    public ResponseEntity<Void> validateToken(@PathVariable("token") String token){

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
