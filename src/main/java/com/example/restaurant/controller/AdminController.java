package com.example.restaurant.controller;

import com.example.restaurant.repository.UserRepository;
import com.example.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static net.sf.jsqlparser.parser.feature.Feature.use;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAdminUserPanelPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin-user-panel";
    }

    @DeleteMapping("/users/delete")
    public String deleteUser(@RequestParam(name = "userId") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/admin/users";
    }
}
