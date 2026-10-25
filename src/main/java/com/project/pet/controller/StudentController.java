package com.project.pet.controller;

import com.project.pet.entity.dto.student.StudentDTO;
import com.project.pet.entity.student.StudentEntity;
import com.project.pet.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PutMapping("/create-student")
    public StudentEntity createStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.addStudent(studentDTO);
    }

    @PutMapping("/update-student/{studentId}")
    public  StudentEntity updateStudent(@PathVariable UUID studentId, @RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(studentId, studentDTO);
    }

    @DeleteMapping("/delete-student/{studentId")
    public void deleteStudent(@PathVariable UUID studentId) {
        studentService.deleteStudentById(studentId);
    }
}
