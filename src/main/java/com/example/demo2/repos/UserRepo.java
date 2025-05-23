package com.example.demo2.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo2.models.User;
import java.util.Optional;


public interface UserRepo extends JpaRepository<User, Integer>{
    Optional<User> findByUsername(String username);
}