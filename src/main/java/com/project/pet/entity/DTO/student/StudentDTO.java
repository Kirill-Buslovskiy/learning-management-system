package com.project.pet.entity.DTO.student;

import com.project.pet.entity.group.GroupEntity;

import java.util.Set;

public record StudentDTO(

        String studentName,

        String studentSurname,

        Set<GroupEntity> groups
        ) {
}
