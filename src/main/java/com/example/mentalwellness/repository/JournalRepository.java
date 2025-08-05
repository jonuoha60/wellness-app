package com.example.mentalwellness.repository;

import com.example.mentalwellness.model.Journal;
import com.example.mentalwellness.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface JournalRepository extends JpaRepository<Journal, Long> {
    Optional<Journal> findByUserAndDate(User user, LocalDate date);
}
