package com.ahmad.user_service.services;

import com.ahmad.user_service.dtos.SignupDto;
import com.ahmad.user_service.models.Token;
import com.ahmad.user_service.models.User;

public interface UserService {

    Token login(String emailAddress, String password);

    User signup(SignupDto signupDto);

    Token createToken(User user);

    void logout(String value);
    

}
