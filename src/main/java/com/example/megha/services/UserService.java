package com.example.megha.services;

import com.example.megha.dto.UserRegistrationDto;
import com.example.megha.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}