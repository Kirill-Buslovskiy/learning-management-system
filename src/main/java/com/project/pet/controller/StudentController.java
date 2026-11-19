package com.project.pet.controller;

import com.project.pet.dto.student.StudentDTO;
import com.project.pet.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
@Tag(name = "Student", description = "Controller of students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/students")
    @Operation(summary = "Add student on site")
    public StudentDTO createStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.addStudent(studentDTO);
    }

    @PostMapping("/students/{id}")
    @Operation(summary = "Update student by id")
    public StudentDTO updateStudent(@PathVariable @Parameter(name = "StudentId") UUID id,
                                    @RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(id, studentDTO);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete student by id")
    public void deleteStudent(@PathVariable @Parameter(name = "studentId") UUID id) {
        studentService.deleteStudentById(id);
    }
}
