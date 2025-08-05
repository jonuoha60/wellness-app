package com.example.mentalwellness.controller;

import com.example.mentalwellness.model.Journal;
import com.example.mentalwellness.model.User;
import com.example.mentalwellness.service.JournalService;
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
import java.util.Optional;

@Controller
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String journalPage(@AuthenticationPrincipal UserDetails userDetails, Model model){
        User user = userService.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Journal journal = journalService.getTodayEntry(user).orElse(new Journal());
        journal.setUser(user);
        model.addAttribute("journal", journal);
        return "journal/entry-form";
    }

    @PostMapping
    public String submitJournal(@ModelAttribute Journal journal, @AuthenticationPrincipal UserDetails userDetails){
        User user = userService.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if there is already a journal entry for today
        Optional<Journal> existingJournalOpt = journalService.getTodayEntry(user);

        Journal journalToSave;

        if(existingJournalOpt.isPresent()){
            //Update existing entry
            journalToSave = existingJournalOpt.get();
            journalToSave.setContent(journal.getContent());
        }else {
            //Create new entry
            journalToSave = journal;
            journalToSave.setUser(user);
            journalToSave.setDate(LocalDate.now());
        }

        journalService.save(journalToSave);
        return "redirect:/dashboard";
    }
}
