package com.example.mentalwellness.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mentalwellness.model.User;

@Controller
public class DashboardController {

@GetMapping("/dashboard")
public String dashboard(Model model, Authentication authentication) {
    String email = authentication.getName(); 
    model.addAttribute("email", email);
    return "dashboard";
}
}
