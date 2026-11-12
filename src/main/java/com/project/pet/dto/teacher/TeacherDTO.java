package com.project.pet.dto.teacher;

import com.project.pet.dto.course.CourseDTO;

import java.util.Set;
import java.util.UUID;

public record TeacherDTO(

        UUID id,

        String firstName,

        String surname,

        Set<CourseDTO> courses
) {
}
