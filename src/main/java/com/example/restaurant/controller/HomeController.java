package com.example.restaurant.controller;

import com.example.restaurant.config.CustomUserDetails;
import com.example.restaurant.dto.UserDto;
import com.example.restaurant.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getHomePage(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        Optional<UserDto> userDtoOptional = userService.findUserById(userDetails.getUserId());
        if (userDtoOptional.isEmpty()) {
            return "redirect:/logout";
        }
        model.addAttribute("user", userDtoOptional.get());
        return "home";
    }

    @PutMapping("/update-user")
    public String updateUser(@AuthenticationPrincipal CustomUserDetails userDetails,
                             @Valid @ModelAttribute("user") UserDto userDto,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/home";
        }
        userService.updateUser(userDto, userDetails.getUserId());
        return "redirect:/home";
    }
}
