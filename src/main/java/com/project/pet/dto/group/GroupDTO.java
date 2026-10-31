package com.project.pet.dto.group;

import com.project.pet.dto.student.StudentDTO;

import java.util.Set;
import java.util.UUID;


public record GroupDTO(

        UUID id,

        String groupName,

        Set<StudentDTO> studentsInGroup
) {
}
