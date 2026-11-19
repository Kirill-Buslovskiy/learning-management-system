package com.project.pet.service;

import com.project.pet.dto.schedule.ScheduleDTO;
import com.project.pet.dto.teacher.TeacherDTO;
import com.project.pet.entity.course.CourseEntity;
import com.project.pet.entity.schedule.ScheduleEntity;
import com.project.pet.entity.teacher.TeacherEntity;
import com.project.pet.exceptions.CourseNotFoundException;
import com.project.pet.exceptions.TeacherNotFoundException;
import com.project.pet.mapper.ScheduleMapper;
import com.project.pet.mapper.TeacherMapper;
import com.project.pet.repository.CourseRepo;
import com.project.pet.repository.ScheduleRepo;
import com.project.pet.repository.TeacherRepo;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {
    @Mock
    private TeacherRepo teacherRepo;
    @Mock
    private TeacherMapper teacherMapper;
    @Mock
    private CourseRepo courseRepo;
    @Mock
    private ScheduleRepo scheduleRepo;
    @Mock
    private ScheduleMapper scheduleMapper;

    @InjectMocks
    private TeacherService teacherService;

    private UUID teacherId;
    private UUID courseId;

    private TeacherEntity teacherEntity;
    private TeacherEntity savedTeacherEntity;

    private TeacherDTO teacherDTO;
    private TeacherDTO outTeacherDto;
    private TeacherDTO updatedTeacherDto;

    private CourseEntity courseEntity;

    private ScheduleEntity scheduleEntity;
    private ScheduleEntity scheduleEntity1;

    private ScheduleDTO scheduleDto;
    private ScheduleDTO scheduleDto1;


    @BeforeEach
    void setUp() {

        teacherId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        courseId = UUID.fromString("321e4567-e89b-12d3-a456-426614174000");
        UUID scheduleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174123");
        UUID scheduleId1 = UUID.fromString("123e4567-e89b-12d3-a456-426614174888");

        teacherDTO = new TeacherDTO(null, "Taisia", "Pavlovna", null);

        teacherEntity = new TeacherEntity();
        teacherEntity.setFirstName(teacherDTO.firstName());
        teacherEntity.setSurname(teacherDTO.surname());

        savedTeacherEntity = new TeacherEntity();
        savedTeacherEntity.setId(teacherId);
        savedTeacherEntity.setFirstName(teacherEntity.getFirstName());
        savedTeacherEntity.setSurname(teacherEntity.getSurname());

        outTeacherDto = new TeacherDTO(savedTeacherEntity.getId(), savedTeacherEntity.getFirstName(), savedTeacherEntity.getSurname(), null);

        updatedTeacherDto = new TeacherDTO(teacherId, "Natalia", "Ivanova", null);

        courseEntity = new CourseEntity();
        courseEntity.setId(courseId);
        courseEntity.setCourseName("Java");
        courseEntity.setDescription("Java Course");

        scheduleEntity = new ScheduleEntity();
        scheduleEntity.setGroup(null);
        scheduleEntity.setCourse(null);
        scheduleEntity.setDateStartLesson(LocalDateTime.of(2026, 11, 19, 23, 38, 0, 0));
        scheduleEntity.setDateEndLesson(LocalDateTime.of(2026, 11, 20, 23, 38, 0, 0));

        scheduleEntity1 = new ScheduleEntity();
        scheduleEntity1.setGroup(null);
        scheduleEntity1.setCourse(null);
        scheduleEntity1.setDateStartLesson(LocalDateTime.of(2026, 11, 21, 23, 38, 0, 0));
        scheduleEntity1.setDateEndLesson(LocalDateTime.of(2026, 11, 22, 23, 38, 0, 0));

        scheduleDto = new ScheduleDTO(scheduleId, null, null, scheduleEntity.getDateStartLesson(), scheduleEntity.getDateEndLesson());
        scheduleDto1 = new ScheduleDTO(scheduleId1, null, null, scheduleEntity1.getDateStartLesson(), scheduleEntity1.getDateEndLesson());

    }

    @Test
    void addTeacher() {

        when(teacherMapper.toEntity(teacherDTO)).thenReturn(teacherEntity);

        when(teacherRepo.save(teacherEntity)).thenReturn(savedTeacherEntity);

        when(teacherMapper.toDto(savedTeacherEntity)).thenReturn(outTeacherDto);

        TeacherDTO actual = teacherService.addTeacher(teacherDTO);

        verify(teacherMapper, times(1)).toEntity(teacherDTO);
        verify(teacherRepo, times(1)).save(teacherEntity);
        verify(teacherMapper, times(1)).toDto(savedTeacherEntity);

        assertSame(outTeacherDto, actual);
        assertEquals(savedTeacherEntity.getId(), actual.id());

    }

    @Test
    void updateTeacher() {

        when(teacherRepo.findById(teacherId)).thenReturn(Optional.of(teacherEntity));

        when(teacherRepo.save(teacherEntity)).thenReturn(savedTeacherEntity);

        when(teacherMapper.toDto(savedTeacherEntity)).thenReturn(updatedTeacherDto);

        TeacherDTO actual = teacherService.updateTeacher(teacherId, teacherDTO);

        assertSame(updatedTeacherDto, actual);
        assertEquals(updatedTeacherDto.firstName(), actual.firstName());
        assertEquals(updatedTeacherDto.surname(), actual.surname());
        assertEquals(updatedTeacherDto.id(), actual.id());
    }

    @Test
    @DisplayName("When update teacher and teacher not found then throw TeacherNotFoundException")
    void whenUpdateTeacherAndTeacherNotFoundThenThrowTeacherNotFoundException() {
        when(teacherRepo.findById(teacherId)).thenReturn(Optional.empty());

        assertThrows(TeacherNotFoundException.class, () -> teacherService.updateTeacher(teacherId, teacherDTO));
    }

    @Test
    void deleteTeacherById() {
        teacherService.deleteTeacherById(teacherId);

        verify(teacherRepo, times(1)).deleteById(teacherId);
    }

    @Test
    void addTeacherToCourse() {

        TeacherEntity spyTeacher = spy(teacherEntity);

        when(teacherRepo.findById(teacherId)).thenReturn(Optional.of(spyTeacher));

        when(courseRepo.findById(courseId)).thenReturn(Optional.of(courseEntity));

        when(teacherRepo.save(spyTeacher)).thenReturn(spyTeacher);

        teacherService.addTeacherToCourse(teacherId, courseId);

        verify(spyTeacher, times(1)).addCourse(courseEntity);
        verify(teacherRepo, times(1)).save(spyTeacher);

        assertTrue(spyTeacher.getCourses().contains(courseEntity));
    }

    @Test
    @DisplayName("When add teacher to course and teacher not found then throw TeacherNotFoundException")
    void whenAddTeacherToCourseAndTeacherNotFoundThenThrowTeacherNotFoundException() {
        when(teacherRepo.findById(teacherId)).thenReturn(Optional.empty());

        assertThrows(TeacherNotFoundException.class, () -> teacherService.addTeacherToCourse(teacherId, courseId));
    }

    @Test
    @DisplayName("When add teacher to course and course not found then throw CourseNotFoundException")
    void whenAddTeacherToCourseAndCourseNotFoundThenThrowCourseNotFoundException() {
        when(teacherRepo.findById(teacherId)).thenReturn(Optional.of(teacherEntity));

        when(courseRepo.findById(courseId)).thenReturn(Optional.empty());

        assertThrows(CourseNotFoundException.class, () -> teacherService.addTeacherToCourse(teacherId, courseId));
    }

    @Test
    void viewClassScheduleForTeacher() {

        List<ScheduleEntity> scheduleEntities = List.of(scheduleEntity, scheduleEntity1);

        PageRequest pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "dateStartLesson"));

        PageImpl<@NotNull ScheduleEntity> schedulePage = new PageImpl<>(scheduleEntities, pageable, 3);

        when(scheduleRepo.viewClassScheduleForTeacher(teacherId, pageable)).thenReturn(schedulePage);

        when(scheduleMapper.toDto(scheduleEntity)).thenReturn(scheduleDto);
        when(scheduleMapper.toDto(scheduleEntity1)).thenReturn(scheduleDto1);

        Page<@NotNull ScheduleDTO> actual = teacherService.viewClassScheduleForTeacher(teacherId, 0, 5);

        verify(scheduleRepo).viewClassScheduleForTeacher(teacherId, pageable);

        assertEquals(2, actual.getContent().size());
        assertEquals(5, actual.getSize());
        assertEquals(2, actual.getTotalElements());
        assertEquals(1, actual.getTotalPages());

        assertEquals(scheduleDto, actual.getContent().get(0));
        assertEquals(scheduleDto1, actual.getContent().get(1));

    }
}