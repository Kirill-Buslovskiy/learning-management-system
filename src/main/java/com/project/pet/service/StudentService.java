package com.project.pet.service;

import com.project.pet.entity.dto.student.StudentDTO;
import com.project.pet.entity.student.StudentEntity;
import com.project.pet.exceptions.StudentNotFoundException;
import com.project.pet.mapper.StudentMapper;
import com.project.pet.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;
    private final StudentMapper studentMapper;

    @Transactional
    public StudentEntity addStudent(StudentDTO studentDto) {
        return studentRepo
                .save(studentMapper.toEntity(studentDto));
    }

    @Transactional
    public StudentEntity updateStudent(UUID id, StudentDTO studentDto) {
        StudentEntity student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id: " + id + "not found"));

        student.setStudentName(studentDto.studentName());
        student.setStudentSurname(studentDto.studentSurname());
        return student;
    }

    @Transactional
    public void deleteStudentById(UUID id) {
        studentRepo.deleteById(id);
    }

    @Transactional
    public void deleteStudent(StudentDTO studentDto) {
        studentRepo.delete(studentMapper.toEntity(studentDto));
    }


}
