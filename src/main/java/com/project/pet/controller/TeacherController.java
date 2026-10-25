package com.project.pet.controller;

import com.project.pet.entity.course.CourseEntity;
import com.project.pet.entity.dto.teacher.TeacherDTO;
import com.project.pet.entity.schedule.ScheduleEntity;
import com.project.pet.entity.teacher.TeacherEntity;
import com.project.pet.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PutMapping("/create-teacher")
    public TeacherEntity createTeacher(@RequestBody TeacherDTO teacherDTO) {
        return teacherService.addTeacher(teacherDTO);
    }


    @PutMapping("/update-teacher/{teacherId}")
    public TeacherEntity updateTeacher(@PathVariable UUID teacherId, @RequestBody TeacherDTO teacherDTO) {
        return teacherService.updateTeacher(teacherId, teacherDTO);
    }

    @DeleteMapping("/delete-teacher/{teacherId}")
    public void deleteTeacher(@PathVariable UUID teacherId) {
        teacherService.deleteTeacherById(teacherId);
    }

    @PutMapping("/add/{teacherId}/course/{courseId}")
    public CourseEntity addTeacherToCourse(@PathVariable UUID teacherId, @PathVariable UUID courseId) {
        return teacherService.addTeacherToCourse(teacherId, courseId);
    }

    @GetMapping("/view-schedule/{teacherId}")
    public ScheduleEntity viewSchedule(@PathVariable UUID teacherId) {
        return teacherService.viewClassScheduleForTeacher(teacherId);
    }
}
