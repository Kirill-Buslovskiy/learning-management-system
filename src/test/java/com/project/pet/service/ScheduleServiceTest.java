package com.project.pet.service;

import com.project.pet.repository.ScheduleRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {

    @Mock
    private ScheduleRepo repo;

    @InjectMocks
    private ScheduleService service;

    private LocalDateTime startTimeForDeleteSchedule;

    @BeforeEach
    void setUp() {
        startTimeForDeleteSchedule = LocalDateTime.of(2026, 11, 19, 17, 20, 0, 0);
    }

    @Test
    void deleteScheduledAfterYear() {

        service.deleteScheduledAfterYear(startTimeForDeleteSchedule);

        verify(repo, times(1)).cleanScheduleAfterYear(startTimeForDeleteSchedule);
    }
}