package com.example.mentalwellness.service;

import com.example.mentalwellness.model.Comment;
import com.example.mentalwellness.model.Journal;
import com.example.mentalwellness.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentsForJournal(Journal journal){
        return commentRepository.findByJournal(journal);
    }

    public Comment save(String content, String alias, Journal journal){
        Comment comment = new Comment();
        comment.setAlias(alias);
        comment.setContent(content);
        comment.setTimestamp(LocalDateTime.now());
        comment.setJournal(journal);
        return commentRepository.save(comment);
    }
}
