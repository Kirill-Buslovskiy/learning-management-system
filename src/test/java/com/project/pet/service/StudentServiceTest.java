package com.project.pet.service;

import com.project.pet.dto.student.StudentDTO;
import com.project.pet.entity.student.StudentEntity;
import com.project.pet.exceptions.StudentNotFoundException;
import com.project.pet.mapper.StudentMapper;
import com.project.pet.repository.StudentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepo repo;
    @Mock
    private StudentMapper mapper;

    @InjectMocks
    private StudentService service;

    private UUID studentId;

    private StudentEntity studentEntity;
    private StudentEntity savedStudentEntity;

    private StudentDTO studentDTO;
    private StudentDTO outStudentDTO;

    @BeforeEach
    void setUp() {
        studentId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        studentEntity = new StudentEntity();
        studentEntity.setStudentName("Oleg");
        studentEntity.setStudentSurname("Kasyanov");

        savedStudentEntity = new StudentEntity();
        savedStudentEntity.setId(studentId);
        savedStudentEntity.setStudentName(studentEntity.getStudentName());
        savedStudentEntity.setStudentSurname(studentEntity.getStudentSurname());

        studentDTO = new StudentDTO(null, studentEntity.getStudentName(), studentEntity.getStudentSurname(), null);

        outStudentDTO = new StudentDTO(studentId, studentDTO.studentName(), studentDTO.studentSurname(), null);
    }

    @Test
    void addStudent() {

        when(mapper.toEntity(studentDTO)).thenReturn(studentEntity);

        when(repo.save(studentEntity)).thenReturn(savedStudentEntity);

        when(mapper.toDto(savedStudentEntity)).thenReturn(outStudentDTO);

        StudentDTO actual = service.addStudent(studentDTO);

        verify(mapper).toEntity(studentDTO);
        verify(repo, times(1)).save(studentEntity);
        verify(mapper, times(1)).toDto(savedStudentEntity);

        assertSame(outStudentDTO, actual);
        assertEquals(savedStudentEntity.getId(), actual.id());

    }

    @Test
    void updateStudent() {

        when(repo.findById(studentId)).thenReturn(Optional.of(studentEntity));

        when(repo.save(studentEntity)).thenReturn(savedStudentEntity);

        when(mapper.toDto(savedStudentEntity)).thenReturn(outStudentDTO);

        StudentDTO actual = service.updateStudent(studentId, studentDTO);

        assertSame(outStudentDTO, actual);
        assertEquals(savedStudentEntity.getStudentName(), actual.studentName());
        assertEquals(savedStudentEntity.getStudentSurname(), actual.studentSurname());
        assertEquals(savedStudentEntity.getId(), actual.id());

    }

    @Test
    @DisplayName("When update student and student not found then throw StudentNotFoundException")
    void whenUpdateStudentAndStudentNotFoundThenThrowStudentNotFoundException() {

        when(repo.findById(studentId)).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> service.updateStudent(studentId, studentDTO));

    }

    @Test
    void deleteStudentById() {

        repo.deleteById(studentId);

        verify(repo, times(1)).deleteById(studentId);
    }
}