package com.project.pet.service;

import com.project.pet.dto.course.CourseDTO;
import com.project.pet.entity.course.CourseEntity;
import com.project.pet.entity.group.GroupEntity;
import com.project.pet.exceptions.CourseNotFoundException;
import com.project.pet.exceptions.GroupNotFoundException;
import com.project.pet.mapper.CourseMapper;
import com.project.pet.repository.CourseRepo;
import com.project.pet.repository.GroupRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepo courseRepo;
    private final CourseMapper courseMapper;
    private final GroupRepo groupRepo;


    @Transactional
    public CourseDTO addCourse(CourseDTO courseDTO) {
        CourseEntity course = courseMapper.toEntity(courseDTO);

        courseRepo.save(course);

        return courseMapper.toDto(course);
    }

    @Transactional
    public CourseDTO updateCourse(UUID id, CourseDTO courseDTO) {
        CourseEntity course = courseRepo.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with id: %s is not found".formatted(id)));

        course.setCourseName(courseDTO.courseName());
        course.setDescription(courseDTO.description());

        courseRepo.save(course);

        return courseMapper.toDto(course);
    }

    @Transactional
    public void deleteCourseById(UUID id) {
        courseRepo.deleteById(id);
    }


    @Transactional
    public void addGroupsToCourses(UUID groupId, UUID coursesId) {
        CourseEntity course = courseRepo.findById(coursesId)
                .orElseThrow(() -> new CourseNotFoundException("Course with id: %s is not found".formatted(coursesId)));

        GroupEntity group = groupRepo.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException("Group with id: %s is not found".formatted(groupId)));

        course.addGroup(group);
        courseRepo.save(course);
    }
}
