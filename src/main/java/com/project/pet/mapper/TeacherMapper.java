package com.project.pet.mapper;

import com.project.pet.entity.dto.teacher.TeacherDTO;
import com.project.pet.entity.teacher.TeacherEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherDTO toDto(TeacherEntity entity);

    TeacherEntity toEntity(TeacherDTO dto);
}
