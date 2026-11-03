package com.project.pet.controller;

import com.project.pet.dto.teacher.TeacherDTO;
import com.project.pet.entity.course.CourseEntity;
import com.project.pet.entity.schedule.ScheduleEntity;
import com.project.pet.entity.teacher.TeacherEntity;
import com.project.pet.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
@Tag(name = "Teacher", description = "Controller of teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/teachers")
    @Operation(summary = "Add teacher on site")
    public TeacherEntity createTeacher(@RequestBody TeacherDTO teacherDTO) {
        return teacherService.addTeacher(teacherDTO);
    }


    @PostMapping("/teachers/{id}")
    @Operation(summary = "Update teacher by id")
    public TeacherEntity updateTeacher(@PathVariable @Parameter(name = "teacherId") UUID id,
                                       @RequestBody TeacherDTO teacherDTO) {
        return teacherService.updateTeacher(id, teacherDTO);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete teacher by id")
    public void deleteTeacher(@PathVariable @Parameter(name = "teacherId") UUID id) {
        teacherService.deleteTeacherById(id);
    }

    @PostMapping("/teachers/{teacherId}/course/{courseId}")
    @Operation(summary = "Add teacher with id to course by id")
    public CourseEntity addTeacherToCourse(@PathVariable @Parameter(name = "teacherId") UUID teacherId,
                                           @PathVariable @Parameter(name = "courseId") UUID courseId) {
        return teacherService.addTeacherToCourse(teacherId, courseId);
    }

    @GetMapping("/schedule/{id}")
    @Operation(summary = "Get view schedule by teacherId")
    public Page<ScheduleEntity> viewSchedule(@PathVariable @Parameter(name = "teacherId") UUID id) {
        return teacherService.viewClassScheduleForTeacher(id, 0, 50);
    }
}
