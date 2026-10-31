package com.project.pet.dto.course;

import com.project.pet.entity.schedule.ScheduleEntity;

import java.util.Set;
import java.util.UUID;

public record CourseDTO(
        UUID id,

        String courseName,

        String description,

        Set<ScheduleEntity> schedules
) {
}
