package com.project.pet.component;

import com.project.pet.repository.ScheduleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ScheduleCleaner {

    private final ScheduleRepo scheduleRepo;

    @Scheduled(cron = "${schedule.cleanup.cron}")
    public void cleanSchedules() {
        Instant now = Instant.from(LocalDateTime.now());

        scheduleRepo.cleanScheduleAfterYear(LocalDateTime.from(now));
    }

}
