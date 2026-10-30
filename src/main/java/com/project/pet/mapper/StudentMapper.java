package com.project.pet.mapper;

import com.project.pet.entity.dto.student.StudentDTO;
import com.project.pet.entity.student.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDTO toDto(StudentEntity entity);

    StudentEntity toEntity(StudentDTO dto);

}

