package com.nsu.thmeleafproject.controller;

import com.nsu.thmeleafproject.model.User;
import com.nsu.thmeleafproject.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showUserLoginForm( Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        System.out.println("login done get");
        return "login";
    }

//    @PostMapping("/login")
//    public String loginUser(String username, String password, HttpSession session, Model model) {
//        User user = userService.loginUser(username, password);
//        session.setAttribute("user", user);
//        System.out.println(user.getUsername());
//        System.out.println("login done post");
//        return "login";
//    }
}
