package com.example.demo2;

import org.springframework.stereotype.Component;

import com.example.demo2.dtos.UserDTO;
import com.example.demo2.services.UserService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UserService userService;
    
    @PostConstruct
	public void saveNewUsers(){
		UserDTO user1 = new UserDTO("arya1","arya1");
		UserDTO user2 = new UserDTO("arya2","arya2");
		UserDTO user3 = new UserDTO("arya3","arya3");
		userService.saveUser(user1);
		userService.saveUser(user2);
		userService.saveUser(user3);
	}

}
