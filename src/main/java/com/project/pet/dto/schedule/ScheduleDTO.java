package com.project.pet.dto.schedule;

import com.project.pet.dto.course.CourseDTO;
import com.project.pet.entity.group.GroupEntity;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record ScheduleDTO(
        UUID id,

        GroupEntity group,

        Set<CourseDTO> courses,

        LocalDateTime dateStartLesson,

        LocalDateTime dateEndLesson
) {
}
