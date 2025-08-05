package com.example.mentalwellness.repository;

import com.example.mentalwellness.model.CheckIn;
import com.example.mentalwellness.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {
    List<CheckIn> findByUser(User user);
    boolean existsByUserAndDate(User user, LocalDate date);
}
