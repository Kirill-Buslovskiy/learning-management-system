package com.project.pet.dto.student;

import com.project.pet.entity.group.GroupEntity;

import java.util.Set;
import java.util.UUID;

public record StudentDTO(

        UUID id,

        String studentName,

        String studentSurname,

        Set<GroupEntity> groups
) {
}
