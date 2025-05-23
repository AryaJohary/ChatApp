package com.example.demo2.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo2.dtos.UserDTO;
import com.example.demo2.models.User;
import com.example.demo2.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        log.info(userDTO.toString());
        Optional<User> user = userService.findUser(userDTO);
        if(!user.isPresent()){
            return new ResponseEntity<>("User "+userDTO.getUsername()+" not found", HttpStatus.NOT_FOUND);
        }
        // TODO - i need to save the user to current authentication context
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody UserDTO userDTO) {
        Optional<User> user = userService.saveUser(userDTO);
        if(!user.isPresent()){
            return new ResponseEntity<>("Username already present.", HttpStatus.EXPECTATION_FAILED);
        }
        // TODO - i would also like to add the user to current authentication context
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }
    
    @GetMapping("usersList")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
