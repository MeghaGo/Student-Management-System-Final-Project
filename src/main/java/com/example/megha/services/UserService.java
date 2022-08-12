package com.example.megha.services;

import com.example.megha.dto.UserRegistrationDto;
import com.example.megha.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    /*Here extending UserService to UserDetailsService provides the user details to the DaoAuthentication
      provider*/

    User save(UserRegistrationDto registrationDto); //interface ma no implementation
}