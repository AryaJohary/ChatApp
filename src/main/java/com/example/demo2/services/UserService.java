package com.example.demo2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo2.dtos.UserDTO;
import com.example.demo2.models.User;
import com.example.demo2.repos.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public Optional<User> saveUser(UserDTO userDTO){
        if(userRepo.findByUsername(userDTO.getUsername()).isPresent()){
            log.warn("User with username "+userDTO.getUsername()+ " already exists.");
            return null;
        }
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        User user = new User(userDTO.getUsername(), encodedPassword);
        return Optional.of(userRepo.save(user));
    }

    public Optional<User> findUser(UserDTO userDTO){
        Optional<User> user = userRepo.findByUsername(userDTO.getUsername());
        if(!user.isPresent()){
            log.warn("User "+userDTO.getUsername()+" not present.");
            return null;
        }
        return user;
    }

}
