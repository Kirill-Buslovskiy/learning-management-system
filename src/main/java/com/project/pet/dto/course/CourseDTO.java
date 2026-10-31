package com.project.pet.dto.course;

import com.project.pet.dto.schedule.ScheduleDTO;

import java.util.Set;
import java.util.UUID;

public record CourseDTO(
        UUID id,

        String courseName,

        String description,

        Set<ScheduleDTO> schedules
) {
}
