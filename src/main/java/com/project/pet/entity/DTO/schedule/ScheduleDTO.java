package com.project.pet.entity.DTO.schedule;

import com.project.pet.entity.course.CourseEntity;
import com.project.pet.entity.group.GroupEntity;
import com.project.pet.entity.teacher.TeacherEntity;

import java.time.LocalDateTime;
import java.util.Set;

public record ScheduleDTO(

        Set<GroupEntity> groupSet,

        TeacherEntity teacher,

        CourseEntity course,

        LocalDateTime dateStartLesson,

        LocalDateTime dateEndLesson
) {
}
