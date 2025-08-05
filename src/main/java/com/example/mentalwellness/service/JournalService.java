package com.example.mentalwellness.service;

import com.example.mentalwellness.model.Journal;
import com.example.mentalwellness.model.User;
import com.example.mentalwellness.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    public Optional<Journal> getTodayEntry(User user){
        return journalRepository.findByUserAndDate(user, LocalDate.now());
    }

    public Journal save(Journal journal){
        return journalRepository.save(journal);
    }
}
