package com.example.mentalwellness.controller;

import com.example.mentalwellness.model.CheckIn;
import com.example.mentalwellness.model.User;
import com.example.mentalwellness.service.CheckInService;
import com.example.mentalwellness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/checkin")
public class CheckInController {

    @Autowired
    private CheckInService checkInService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String checkInForm(Model model){
        model.addAttribute("checkIn", new CheckIn());
        return "journal/checkIn";
    }

    @PostMapping
    public String submitCheckIn(@ModelAttribute CheckIn checkIn, @AuthenticationPrincipal UserDetails userDetails){
        User user = userService.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        checkIn.setUser(user);
        checkIn.setDate(LocalDate.now());
        checkInService.save(checkIn);
        return "redirect:/dashboard";
    }
}
