package com.project.pet.mapper;

import com.project.pet.entity.course.CourseEntity;
import com.project.pet.entity.dto.course.CourseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseEntity toDto(CourseEntity entity);

    CourseEntity toEntity(CourseDTO dto);

}
