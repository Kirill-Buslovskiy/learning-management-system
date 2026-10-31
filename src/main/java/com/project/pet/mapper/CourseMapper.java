package com.project.pet.mapper;

import com.project.pet.entity.course.CourseEntity;
import com.project.pet.dto.course.CourseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDTO toDto(CourseEntity entity);

    CourseEntity toEntity(CourseDTO dto);

}
