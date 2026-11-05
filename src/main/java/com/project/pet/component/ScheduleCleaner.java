package com.project.pet.component;

import com.project.pet.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ScheduleCleaner {

    private final ScheduleService scheduleService;

    @Transactional
    @Scheduled(cron = "${schedule.cleanup.cron}")
    public void cleanSchedules() {
        Instant now = Instant.from(LocalDateTime.now());

        scheduleService.deleteScheduledAfterYear(LocalDateTime.from(now));
    }

}
