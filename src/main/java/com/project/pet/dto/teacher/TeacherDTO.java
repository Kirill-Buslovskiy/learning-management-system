package com.project.pet.dto.teacher;

import com.project.pet.entity.course.CourseEntity;

import java.util.Set;
import java.util.UUID;

public record TeacherDTO(

        UUID id,

        String firstName,

        String surname,

        Set<CourseEntity> courses
) {
}
