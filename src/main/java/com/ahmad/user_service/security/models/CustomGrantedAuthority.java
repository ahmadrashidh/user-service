package com.ahmad.user_service.security.models;

import com.ahmad.user_service.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
@NoArgsConstructor
@Setter
@Getter
public class CustomGrantedAuthority implements GrantedAuthority {

    private String authority;

    public CustomGrantedAuthority(Role role) {
        this.authority = role.getName();
    }

}
