package com.project.pet.service;

import com.project.pet.dto.course.CourseDTO;
import com.project.pet.dto.schedule.ScheduleDTO;
import com.project.pet.dto.teacher.TeacherDTO;
import com.project.pet.entity.course.CourseEntity;
import com.project.pet.entity.teacher.TeacherEntity;
import com.project.pet.exceptions.CourseNotFoundException;
import com.project.pet.exceptions.TeacherNotFoundException;
import com.project.pet.mapper.CourseMapper;
import com.project.pet.mapper.TeacherMapper;
import com.project.pet.repository.CourseRepo;
import com.project.pet.repository.ScheduleRepo;
import com.project.pet.repository.TeacherRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepo teacherRepo;
    private final TeacherMapper teacherMapper;
    private final CourseRepo courseRepo;
    private final ScheduleRepo scheduleRepo;
    private final CourseMapper courseMapper;

    @Transactional
    public TeacherDTO addTeacher(TeacherDTO teacherDTO) {
        TeacherEntity entity = teacherMapper.toEntity(teacherDTO);
        TeacherEntity saved = teacherRepo.save(entity);
        return teacherMapper.toDto(saved);

    }

    @Transactional
    public TeacherDTO updateTeacher(UUID id, TeacherDTO teacherDTO) {
        TeacherEntity teacher = teacherMapper.toEntity(getTeacher(id));

        teacher.setFirstName(teacherDTO.firstName());
        teacher.setSurname(teacherDTO.surname());

        teacherRepo.save(teacher);
        return teacherMapper.toDto(teacher);
    }

    @Transactional
    public void deleteTeacherById(UUID id) {
        teacherRepo.deleteById(id);
    }

    @Transactional
    public CourseDTO addTeacherToCourse(UUID teacherId, UUID courseId) {

        TeacherEntity teacher = teacherMapper.toEntity(getTeacher(teacherId));
        CourseEntity course = courseMapper.toEntity(getCourse(courseId));

        teacher.addCourse(course);

        teacherRepo.save(teacher);
        courseRepo.save(course);

        return courseMapper.toDto(course);
    }

    @Transactional
    public Page<ScheduleDTO> viewClassScheduleForTeacher(UUID teacherId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("dateStartLesson").ascending());

        return scheduleRepo.viewClassScheduleForTeacher(teacherId, pageable);
    }


    private TeacherDTO getTeacher(UUID teacherId) {
        return teacherMapper.toDto(teacherRepo.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException("Student with id: " + teacherId + "not found")));
    }

    private CourseDTO getCourse(UUID courseId) {
        return courseMapper.toDto(courseRepo.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Teacher with id: " + courseId + "not found")));
    }
}
