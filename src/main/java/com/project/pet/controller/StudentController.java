package com.project.pet.controller;

import com.project.pet.dto.student.StudentDTO;
import com.project.pet.entity.student.StudentEntity;
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

    @PutMapping("/create-student")
    @Operation(summary = "Add student on site")
    public StudentEntity createStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.addStudent(studentDTO);
    }

    @PutMapping("/update-student/{studentId}")
    @Operation(summary = "Update student by id")
    public StudentEntity updateStudent(@PathVariable @Parameter(name = "studentId") UUID studentId,
                                       @RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(studentId, studentDTO);
    }

    @DeleteMapping("/delete-student/{studentId}")
    @Operation(summary = "Delete student by id")
    public void deleteStudent(@PathVariable @Parameter(name = "studentId") UUID studentId) {
        studentService.deleteStudentById(studentId);
    }
}
