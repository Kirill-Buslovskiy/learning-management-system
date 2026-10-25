package com.project.pet.controller;

import com.project.pet.entity.course.CourseEntity;
import com.project.pet.entity.dto.course.CourseDTO;
import com.project.pet.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PutMapping("/add")
    public CourseEntity addCourse(@RequestBody CourseDTO courseDTO) {
        return courseService.addCourse(courseDTO);
    }

    @PutMapping("/update-course/{courseId}")
    public CourseEntity updateCourse(@PathVariable UUID courseId,
                                     @RequestBody CourseDTO courseDTO) {
        return courseService.updateCourse(courseId, courseDTO);
    }

    @PutMapping("/delete-course/{courseId}")
    public void deleteCourseById(@PathVariable UUID courseId) {
        courseService.deleteCourseById(courseId);
    }

    @PutMapping("/add-group/{groupId}/courses/{coursesId}")
    public void addGroupsToCourse(@PathVariable UUID groupId,
                                  @PathVariable UUID coursesId) {
        courseService.addGroupsToCourses(groupId, coursesId);
    }
}
