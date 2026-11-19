package com.project.pet.service;

import com.project.pet.dto.course.CourseDTO;
import com.project.pet.entity.course.CourseEntity;
import com.project.pet.entity.group.GroupEntity;
import com.project.pet.exceptions.CourseNotFoundException;
import com.project.pet.exceptions.GroupNotFoundException;
import com.project.pet.mapper.CourseMapper;
import com.project.pet.repository.CourseRepo;
import com.project.pet.repository.GroupRepo;
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
class CourseServiceTest {
    @Mock
    private CourseRepo courseRepo;
    @Mock
    private CourseMapper mapper;
    @Mock
    private GroupRepo groupRepo;

    @InjectMocks
    private CourseService service;

    private CourseDTO courseDTO;
    private CourseDTO outCourseDTO;
    private CourseDTO updatedCourseDto;
    private CourseEntity courseEntity;
    private CourseEntity savedCourseEntity;
    private GroupEntity groupEntity;
    private UUID courseId;
    private UUID groupId;

    @BeforeEach
    void setUp() {
        courseId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        groupId = UUID.fromString("123e4567-e89b-24d6-a456-426614174000");

        courseEntity = new CourseEntity();
        courseEntity.setCourseName("Java");
        courseEntity.setDescription("course for Java development");

        savedCourseEntity = new CourseEntity();
        savedCourseEntity.setId(courseId);
        savedCourseEntity.setCourseName(courseEntity.getCourseName());
        savedCourseEntity.setDescription(courseEntity.getDescription());

        courseDTO = new CourseDTO(null, "Java", "course for Java development");

        outCourseDTO = new CourseDTO(courseId, "Java", "course for Java development");

        updatedCourseDto = new CourseDTO(courseId, "updated Java", "updated course for Java development");

        groupEntity = new GroupEntity();
        groupEntity.setGroupName("Java development");

        GroupEntity savedGroupEntity = new GroupEntity();
        savedGroupEntity.setId(groupId);
        savedGroupEntity.setGroupName(groupEntity.getGroupName());
    }

    @Test
    void addCourse() {

        when(mapper.toEntity(courseDTO)).thenReturn(courseEntity);

        when(courseRepo.save(courseEntity)).thenReturn(savedCourseEntity);

        when(mapper.toDto(savedCourseEntity)).thenReturn(outCourseDTO);

        CourseDTO result = service.addCourse(courseDTO);

        verify(mapper).toEntity(courseDTO);
        verify(courseRepo, times(1)).save(courseEntity);
        verify(mapper, times(1)).toDto(savedCourseEntity);

        assertSame(outCourseDTO, result);
        assertEquals(savedCourseEntity.getId(), result.id());
    }

    @Test
    void updateCourse() {

        when(courseRepo.findById(courseId)).thenReturn(Optional.of(courseEntity));
        when(courseRepo.save(courseEntity)).thenReturn(savedCourseEntity);
        when(mapper.toDto(courseEntity)).thenReturn(updatedCourseDto);

        CourseDTO result = service.updateCourse(courseId, courseDTO);

        assertSame(updatedCourseDto, result);
        assertEquals(updatedCourseDto.courseName(), result.courseName());
        assertEquals(updatedCourseDto.description(), result.description());
        assertEquals(savedCourseEntity.getId(), result.id());
    }

    @Test
    @DisplayName("When update course and course not found then throw CourseNotFound exception")
    void whenUpdateCourseAndCourseNotFoundThenThrowCourseNotFoundException() {

        when(courseRepo.findById(courseId)).thenReturn(Optional.empty());

        assertThrows(CourseNotFoundException.class, () -> service.updateCourse(courseId, courseDTO));
    }

    @Test
    void deleteCourseById() {

        service.deleteCourseById(courseId);

        verify(courseRepo, times(1)).deleteById(courseId);
    }

    @Test
    void addGroupsToCourses() {

        CourseEntity courseSpy = spy(courseEntity);

        when(courseRepo.findById(courseId)).thenReturn(Optional.of(courseSpy));

        when(groupRepo.findById(groupId)).thenReturn(Optional.of(groupEntity));

        when(courseRepo.save(courseSpy)).thenReturn(courseSpy);

        service.addGroupsToCourses(groupId, courseId);

        verify(courseSpy, times(1)).addGroup(groupEntity);
        verify(courseRepo, times(1)).save(courseSpy);
        assertTrue(courseSpy.getGroupOnCourses().contains(groupEntity));
    }

    @Test
    @DisplayName("When add groups on course and course not found then return CourseNotFound exception")
    void whenAddGroupsOnCourseAndCourseNotFoundThenReturnCourseNotFoundException() {
        when(courseRepo.findById(courseId)).thenReturn(Optional.empty());

        assertThrows(CourseNotFoundException.class, () -> service.addGroupsToCourses(groupId, courseId));
    }

    @Test
    @DisplayName("When add groups on curse and group not found then return GroupNotFound exception")
    void whenAddGroupsOnCurseAndGroupNotFoundThenReturnGroupNotFoundException() {
        when(courseRepo.findById(courseId)).thenReturn(Optional.of(courseEntity));

        when(groupRepo.findById(groupId)).thenReturn(Optional.empty());

        assertThrows(GroupNotFoundException.class, () -> service.addGroupsToCourses(groupId, courseId));
    }

}