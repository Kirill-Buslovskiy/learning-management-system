package com.project.pet.controller;

import com.project.pet.entity.course.CourseEntity;
import com.project.pet.entity.dto.course.CourseDTO;
import com.project.pet.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
@Tag(name = "Courses", description = "Control of courses")
public class CourseController {

    private final CourseService courseService;

    @PutMapping("/add")
    @Operation(summary = "Add course on site")
    public CourseEntity addCourse(@RequestBody CourseDTO courseDTO) {
        return courseService.addCourse(courseDTO);
    }

    @PutMapping("/update-course/{courseId}")
    @Operation(summary = "Update course by id")
    public CourseEntity updateCourse(@PathVariable @Parameter(name = "courseId") UUID courseId,
                                     @RequestBody CourseDTO courseDTO) {
        return courseService.updateCourse(courseId, courseDTO);
    }

    @PutMapping("/delete-course/{courseId}")
    @Operation(summary = "Delete course by id")
    public void deleteCourseById(@PathVariable @Parameter(name = "courseId") UUID courseId) {
        courseService.deleteCourseById(courseId);
    }

    @PutMapping("/add-group/{groupId}/courses/{coursesId}")
    @Operation(summary = "Add groups to course")
    public void addGroupsToCourse(@PathVariable @Parameter(name = "groupId") UUID groupId,
                                  @PathVariable @Parameter(name = "courseId") UUID coursesId) {
        courseService.addGroupsToCourses(groupId, coursesId);
    }
}
