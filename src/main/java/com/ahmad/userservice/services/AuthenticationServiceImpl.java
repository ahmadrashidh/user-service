package com.ahmad.userservice.services;

import com.ahmad.userservice.dtos.LoginRequestDto;
import com.ahmad.userservice.dtos.TokenDto;
import com.ahmad.userservice.dtos.SignupRequestDto;
import com.ahmad.userservice.exceptions.UserNotExistsException;
import com.ahmad.userservice.models.Token;
import com.ahmad.userservice.models.User;
import com.ahmad.userservice.repositories.TokenRepository;
import com.ahmad.userservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    private UserRepository userRepo;
    private TokenRepository tokenRepo;

    public AuthenticationServiceImpl(UserRepository userRepo, TokenRepository tokenRepo){
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
    }

    @Override
    public TokenDto loginUser(LoginRequestDto loginReqDto) {
        Optional<User> userOpt = this.userRepo.findByEmailAddress(loginReqDto.getEmailAddress());
        if(userOpt.isPresent()){
            Token token = this.createToken(userOpt.get());
            return this.getTokenDtoFromToken(token);
        }

        throw new UserNotExistsException("User with email address" + loginReqDto.getEmailAddress() + " doesn't exist");
    }

    private TokenDto getTokenDtoFromToken(Token token) {
        TokenDto tokenDto = new TokenDto();
        tokenDto.setTokenId(token.getId());
        tokenDto.setTokenValue(token.getValue());
        return tokenDto;
    }

    @Override
    public User createUser(SignupRequestDto signupReqDto) {
        System.out.println(signupReqDto.getEmailAddress());
        User user = this.getUserFromSignupRequestDto(signupReqDto);
        System.out.println(user.getEmailAddress());
        return this.userRepo.save(user);
    }

    private User getUserFromSignupRequestDto(SignupRequestDto signupReqDto) {
        User user = new User();
        user.setName(signupReqDto.getName());
        user.setEmailAddress(signupReqDto.getEmailAddress());
        user.setHashedPassword(signupReqDto.getPassword());

        return user;
    }

    @Override
    public Token createToken(User user) {
        Token token = new Token();
        token.setUser(user);
        token.setExpireAt(token.calculateNextExpiryDate());
        token.setValue(user.getEmailAddress() + "$" + token.getExpireAt().getTime());
        return this.tokenRepo.save(token);
    }

    @Override
    public void invalidateToken(TokenDto tokenDto) {
        this.tokenRepo.deleteById(tokenDto.getTokenId());
    }
}
