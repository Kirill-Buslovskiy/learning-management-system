package com.project.pet.entity.dto.schedule;

import com.project.pet.entity.course.CourseEntity;
import com.project.pet.entity.group.GroupEntity;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record ScheduleDTO(
        UUID id,

        GroupEntity group,

        Set<CourseEntity> courses,

        LocalDateTime dateStartLesson,

        LocalDateTime dateEndLesson
) {
}
