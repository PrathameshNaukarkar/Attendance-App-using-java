package com.example.attendance.controller;

import com.example.attendance.model.User;
import com.example.attendance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/users/{id}")
    public String viewUserProfile(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-profile";
    }

    @PostMapping("/users/{id}/update")
    public String updateUserProfile(@PathVariable Long id, @RequestParam String name, @RequestParam String email) {
        userService.updateUserProfile(id, name, email);
        return "redirect:/users/" + id;
    }
}