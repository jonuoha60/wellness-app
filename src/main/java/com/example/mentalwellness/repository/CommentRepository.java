package com.example.mentalwellness.repository;

import com.example.mentalwellness.model.Comment;
import com.example.mentalwellness.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByJournal(Journal journal);
}
