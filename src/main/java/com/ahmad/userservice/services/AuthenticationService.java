package com.ahmad.userservice.services;

import com.ahmad.userservice.dtos.LoginRequestDto;
import com.ahmad.userservice.dtos.TokenDto;
import com.ahmad.userservice.dtos.SignupRequestDto;
import com.ahmad.userservice.models.Token;
import com.ahmad.userservice.models.User;

public interface AuthenticationService {

    TokenDto loginUser(LoginRequestDto loginReqDto);

    User createUser(SignupRequestDto signupReqDto);

    Token createToken(User user);

    void invalidateToken(TokenDto tokenDto);

}
