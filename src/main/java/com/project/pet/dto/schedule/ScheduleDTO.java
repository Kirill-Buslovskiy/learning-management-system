package com.project.pet.dto.schedule;

import com.project.pet.dto.course.CourseDTO;
import com.project.pet.dto.group.GroupDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record ScheduleDTO(
        UUID id,

        GroupDTO group,

        CourseDTO course,

        LocalDateTime dateStartLesson,

        LocalDateTime dateEndLesson
) {
}
