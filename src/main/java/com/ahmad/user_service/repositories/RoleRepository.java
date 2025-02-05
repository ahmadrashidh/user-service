package com.ahmad.user_service.repositories;

import com.ahmad.user_service.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    public Optional<Role> findByName(String name);
}
