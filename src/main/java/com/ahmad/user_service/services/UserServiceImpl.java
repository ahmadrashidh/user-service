package com.ahmad.user_service.services;

import com.ahmad.user_service.dtos.SignupDto;
import com.ahmad.user_service.exceptions.IncorrectUsernamePasswordException;
import com.ahmad.user_service.models.Role;
import com.ahmad.user_service.models.Token;
import com.ahmad.user_service.models.User;
import com.ahmad.user_service.repositories.RoleRepository;
import com.ahmad.user_service.repositories.TokenRepository;
import com.ahmad.user_service.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;
    private RoleRepository roleRepo;
    private TokenRepository tokenRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepo, TokenRepository tokenRepo, RoleRepository roleRepo, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
        this.roleRepo = roleRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Token login(String emailAddress, String password) {
        Optional<User> userOpt = this.userRepo.findByEmailAddress(emailAddress);
        if(userOpt.isPresent()){
            if(bCryptPasswordEncoder.matches(password, userOpt.get().getHashedPassword()))
                return this.createToken(userOpt.get());
        }

        throw new IncorrectUsernamePasswordException("username or password is incorrect");
    }

    @Override
    public User signup(SignupDto signupDto) {
        User user = new User();
        user.setName(signupDto.getName());
        user.setEmailAddress(signupDto.getEmailAddress());
        user.setHashedPassword(bCryptPasswordEncoder.encode(signupDto.getPassword()));

        Optional<Role> roleOpt = this.roleRepo.findByName("CUSTOMER");
        if(roleOpt.isEmpty()){
            Role role = new Role("CUSTOMER");
            this.roleRepo.save(role);
            user.setRoles(Set.of(role));
        } else {
            user.setRoles(Set.of(roleOpt.get()));
        }

        return this.userRepo.save(user);
    }

    @Override
    public Token createToken(User user) {
        Token token = new Token();
        token.setUser(user);
        token.setExpireAt(token.calculateNextExpiryDate());
        // random until oauth2 is implemented
        token.setValue(RandomStringUtils.randomAlphanumeric(128));
        return this.tokenRepo.save(token);
    }

    @Override
    public void logout(String value) {
        Optional<Token> tokenOpt = this.tokenRepo.findByValueAndDeletedEquals(value, false);
        if(tokenOpt.isPresent()){
           Token token = tokenOpt.get();
           token.setDeleted(true);
           this.tokenRepo.save(token);
        }
    }


}
