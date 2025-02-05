package com.ahmad.user_service.security.models;

import com.ahmad.user_service.models.Role;
import com.ahmad.user_service.models.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {

    @Getter
    User user;

    @Setter
    private List<CustomGrantedAuthority> authorities;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<CustomGrantedAuthority> grantedAuthorities = new ArrayList<>();

        for(Role role: user.getRoles()) {
            log.info(role.getName());
            grantedAuthorities.add(new CustomGrantedAuthority(role));
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.user.getHashedPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmailAddress();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
