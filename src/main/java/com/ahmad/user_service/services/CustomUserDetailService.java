package com.ahmad.user_service.services;

import com.ahmad.user_service.models.User;
import com.ahmad.user_service.repositories.UserRepository;
import com.ahmad.user_service.security.models.CustomUserDetails;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmailAddress(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Force Hibernate to load roles before session closes
        user.getRoles().size();

        return new CustomUserDetails(user);

    }
}
