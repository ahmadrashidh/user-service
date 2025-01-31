package com.ahmad.user_service.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@JsonDeserialize
public class User extends Base{
    private String name;
    private String emailAddress;
    private String hashedPassword;

    @ManyToMany
    private Set<Role> roles = new HashSet<>();

    private boolean isEmailVerified;

    @JsonProperty("roles")
    public Set<Role> getRoles() {
        return new HashSet<>(roles);
    }


    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }
}
