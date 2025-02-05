package com.ahmad.user_service.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonDeserialize
@AllArgsConstructor
@NoArgsConstructor
public class Role extends Base {

    private String name;
}
