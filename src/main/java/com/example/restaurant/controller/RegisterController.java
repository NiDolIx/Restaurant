package com.example.restaurant.controller;

import com.example.restaurant.model.User;
import com.example.restaurant.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("register")
public class RegisterController {
    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String saveUser(@Valid User user, BindingResult bindingResult) {
        if (!user.getUserPassword().equals(user.getUserPasswordConfirm())) {
            bindingResult.rejectValue(
                    "userPasswordConfirm",
                    "error.userPasswordConfirm",
                    "Пароли не совпадают!");
        }
        if (userService.checkUserPhoneNumber(user.getUserPhoneNumber())) {
            bindingResult.rejectValue(
                    "userPhoneNumber",
                    "error.userPhoneNumber",
                    "Номер телефона уже зарегистрирован!");
        }
        if (bindingResult.hasErrors()) {
            return "register";
        } else {
            userService.saveUser(user);
            return "redirect:/login";
        }
    }

    @GetMapping
    public String getRegisterPage() {
        return "register";
    }

    @ModelAttribute("user")
    public User getUser() {
        return new User();
    }
}
