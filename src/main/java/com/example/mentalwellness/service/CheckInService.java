package com.example.mentalwellness.service;

import com.example.mentalwellness.model.CheckIn;
import com.example.mentalwellness.model.User;
import com.example.mentalwellness.repository.CheckInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CheckInService {

    @Autowired
    private CheckInRepository checkInRepository;

    public boolean hasCheckedInToday(User user){
        return checkInRepository.existsByUserAndDate(user, LocalDate.now());
    }

    public CheckIn save(CheckIn checkIn){
        return checkInRepository.save(checkIn);
    }

    public List<CheckIn> getUserCheckIns(User user){
        return checkInRepository.findByUser(user);
    }
}
