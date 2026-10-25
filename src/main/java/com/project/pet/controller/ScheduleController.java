package com.project.pet.controller;

import com.project.pet.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @DeleteMapping("/delete")
    public void cleanScheduleAfterYear(@RequestBody LocalDateTime time) {
        scheduleService.deleteScheduledAfterYear(time);
    }
}
