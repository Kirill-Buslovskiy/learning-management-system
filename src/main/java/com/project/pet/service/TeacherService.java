package com.project.pet.service;

import com.project.pet.entity.course.CourseEntity;
import com.project.pet.entity.dto.teacher.TeacherDTO;
import com.project.pet.entity.schedule.ScheduleEntity;
import com.project.pet.entity.teacher.TeacherEntity;
import com.project.pet.exceptions.CourseNotFoundException;
import com.project.pet.exceptions.TeacherNotFoundException;
import com.project.pet.mapper.TeacherMapper;
import com.project.pet.repository.CourseRepo;
import com.project.pet.repository.ScheduleRepo;
import com.project.pet.repository.TeacherRepo;
import lombok.RequiredArgsConstructor;
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

    @Transactional
    public TeacherEntity addTeacher(TeacherDTO teacherDTO) {
        return teacherRepo
                .save(teacherMapper.toEntity(teacherDTO));
    }

    @Transactional
    public TeacherEntity updateTeacher(UUID id, TeacherDTO teacherDTO) {
        TeacherEntity teacher = getTeacher(id);

        teacher.setFirstName(teacherDTO.firstName());
        teacher.setSurname(teacherDTO.surname());
        return teacherRepo.save(teacher);
    }

    @Transactional
    public void deleteTeacherById(UUID id) {
        teacherRepo.deleteById(id);
}

    @Transactional
    public CourseEntity addTeacherToCourse(UUID teacherId, UUID courseId) {
        TeacherEntity teacher = getTeacher(teacherId);

        CourseEntity course = getCourse(courseId);

        teacher.addCourse(course);
        teacherRepo.save(teacher);

        return courseRepo.save(course);
    }

    @Transactional
    public ScheduleEntity viewClassScheduleForTeacher(UUID teacherId) {
        return scheduleRepo.viewClassScheduleForTeacher(teacherId);


    }


    private TeacherEntity getTeacher(UUID teacherId) {
        return teacherRepo.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException("Student with id: " + teacherId + "not found"));
    }

    private CourseEntity getCourse(UUID courseId) {
        return courseRepo.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Teacher with id: " + courseId + "not found"));
    }
}
