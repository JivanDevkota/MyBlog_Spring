package com.nsu.thmeleafproject.controller;

import com.nsu.thmeleafproject.model.User;
import com.nsu.thmeleafproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showUserRegisterForm(User user,Model model) {
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        userService.registerUser(user);
        System.out.println(user.getRole());
        model.addAttribute("message", "User registered successfully");
        return "register";
    }


//    @PostMapping("/login")
//    public String loginUser(String username, String password, Model model) {
//        User user = userService.loginUser(username);
//
//        return "home";
//        if (user != null && userService.isPasswordValid(user, password)) {
//            // Logic to set the user session can go here if using session management
//            return "redirect:/home";  // Redirect to a home or dashboard page upon successful login
//        } else {
//            model.addAttribute("error", "Invalid username or password");
//            return "login";
//        }

}
