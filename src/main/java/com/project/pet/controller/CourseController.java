package com.project.pet.controller;

import com.project.pet.dto.course.CourseDTO;
import com.project.pet.entity.course.CourseEntity;
import com.project.pet.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @PostMapping("/courses")
    @Operation(summary = "Add course on site")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course is created successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid parameter."),
            @ApiResponse(responseCode = "404", description = "Course not found."),
            @ApiResponse(responseCode = "500", description = "Something wrong on server."),
    })
    public CourseEntity addCourse(@RequestBody CourseDTO courseDTO) {
        return courseService.addCourse(courseDTO);
    }

    @PostMapping("/courses/{id}")
    @Operation(summary = "Update course by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course is updated successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid parameter."),
            @ApiResponse(responseCode = "404", description = "Course not found."),
            @ApiResponse(responseCode = "500", description = "Something wrong on server.")
    })
    public CourseEntity updateCourse(@PathVariable @Parameter(name = "courseId") UUID id,
                                     @RequestBody CourseDTO courseDTO) {
        return courseService.updateCourse(id, courseDTO);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete course by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Course deleted successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid parameter."),
            @ApiResponse(responseCode = "404", description = "Course not found."),
            @ApiResponse(responseCode = "500", description = "Something wrong on server.")
    })
    public void deleteCourseById(@PathVariable @Parameter(name = "courseId") UUID id) {
        courseService.deleteCourseById(id);
    }

    @PostMapping("/courses/{coursesId}/groups/{groupId}")
    @Operation(summary = "Add groups to course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Groups added to course successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid parameter."),
            @ApiResponse(responseCode = "404", description = "Group or curse not found."),
            @ApiResponse(responseCode = "500", description = "Something wrong on server.")
    })
    public void addGroupsToCourse(@PathVariable @Parameter(name = "groupId") UUID groupId,
                                  @PathVariable @Parameter(name = "courseId") UUID coursesId) {
        courseService.addGroupsToCourses(groupId, coursesId);
    }
}
