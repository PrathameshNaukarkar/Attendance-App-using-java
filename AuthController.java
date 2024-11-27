package com.example.attendance.controller;

import com.example.attendance.model.User;
import com.example.attendance.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        authService.register(user.getName(), user.getEmail(), user.getPassword(), "ROLE_STUDENT");
        model.addAttribute("message", "Registration successful! Please login.");
        return "login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, Model model) {
        User user = authService.login(email, password);
        if (user != null) {
            model.addAttribute("user", user);
            return "redirect:/attendance";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }
}