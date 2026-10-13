package com.project.pet.entity.DTO.group;

import com.project.pet.entity.DTO.student.StudentDTO;

import java.util.Set;


public record GroupDTO(

        String groupName,

        Set<StudentDTO> studentsInGroup
) {
}
