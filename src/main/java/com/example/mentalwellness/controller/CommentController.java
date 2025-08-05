package com.example.mentalwellness.controller;

import com.example.mentalwellness.model.Journal;
import com.example.mentalwellness.service.CommentService;
import com.example.mentalwellness.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private JournalService journalService;

    @GetMapping("/{journalId}")
    public String viewComments(@PathVariable Long journalId, Model model){
        Journal journal = new Journal();
        journal.setId(journalId);
        model.addAttribute("comments", commentService.getCommentsForJournal(journal));
        return "journal/comments";
    }

    @PostMapping("/{journalId}")
    public String submitComment(@PathVariable Long journalId, @RequestParam String alias, @RequestParam String content){
        Journal journal = new Journal();
        journal.setId(journalId);
        commentService.save(content, alias, journal);
        return "redirect:/comments/" + journalId;
    }
}
