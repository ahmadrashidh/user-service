package com.ahmad.userservice.services;

import com.ahmad.userservice.dtos.SignupDto;
import com.ahmad.userservice.models.Token;
import com.ahmad.userservice.models.User;

public interface UserService {

    Token login(String emailAddress, String password);

    User signup(SignupDto signupDto);

    Token createToken(User user);

    void logout(String value);

}
