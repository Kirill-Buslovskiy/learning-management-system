package com.project.pet.service;

import com.project.pet.repository.ScheduleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepo scheduleRepo;

    @Transactional
    public void deleteScheduledAfterYear(LocalDateTime now) {
        scheduleRepo.cleanScheduleAfterYear(now);
    }

}
