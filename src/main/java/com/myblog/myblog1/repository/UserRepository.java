package com.myblog.myblog1.repository;

import com.myblog.myblog1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User>findByUsernameOrEmail(String Username,String email);
    Optional<User>findByUsername(String Username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
