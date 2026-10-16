package com.project.pet.entity.dto.group;

import com.project.pet.entity.dto.student.StudentDTO;

import java.util.Set;
import java.util.UUID;


public record GroupDTO(

        UUID id,

        String groupName,

        Set<StudentDTO> studentsInGroup
) {
}
