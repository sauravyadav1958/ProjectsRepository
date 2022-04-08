package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CustomUserDetailsService {
 
    @Autowired
    private UserRepository userRepo;
     

    public User login(String email, String password) {
   
    	User  user = userRepo.findByEmail(email, password);
    	return user;
        
    }
 
}

