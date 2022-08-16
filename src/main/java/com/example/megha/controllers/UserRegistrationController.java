package com.example.megha.controllers;


import com.example.megha.dto.UserRegistrationDto;
import com.example.megha.model.User;
import com.example.megha.repository.UserRepository;
import com.example.megha.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    @Autowired
    private UserRepository userRepository;
    private UserRegistrationDto userRegistrationDto;
    private UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }


    @ModelAttribute("user")

    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(
            @ModelAttribute("user") UserRegistrationDto registrationDto
    ) {
        Optional<User> optionalUser= Optional.ofNullable(userRepository.findByEmail(registrationDto.getEmail()));
        if(optionalUser.isPresent()){
            return "redirect:/registration?error";
        }
        userService.save(registrationDto);
        return "redirect:/registration?success";
    }
}