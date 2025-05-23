package com.example.demo2.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo2.dtos.UserDTO;
import com.example.demo2.models.User;
import com.example.demo2.repos.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequiredArgsConstructor
@RequestMapping("/custom")
@Slf4j
public class LoginController {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    
    @PostMapping("/login")
    public User login(@RequestBody UserDTO userDTO) {
        log.info(userDTO.toString());
        return userRepo.save(new User(userDTO));
    }

    @PostMapping("signup")
    public User signup(@RequestBody UserDTO userDTO) {
        User newUser = new User(userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()));
        return userRepo.save(newUser);
    }
    
    

}
