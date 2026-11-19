package com.project.pet.dto.student;

import com.project.pet.dto.group.GroupDTO;

import java.util.Set;
import java.util.UUID;

public record StudentDTO(

        UUID id,

        String studentName,

        String studentSurname,

        Set<GroupDTO> groups
) {
}
