package com.project.pet.dto.course;

import java.util.UUID;

public record CourseDTO(
        UUID id,

        String courseName,

        String description

) {
}
