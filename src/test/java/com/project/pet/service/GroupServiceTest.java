package com.project.pet.service;

import com.project.pet.dto.group.GroupDTO;
import com.project.pet.dto.schedule.ScheduleDTO;
import com.project.pet.entity.group.GroupEntity;
import com.project.pet.entity.schedule.ScheduleEntity;
import com.project.pet.entity.student.StudentEntity;
import com.project.pet.exceptions.GroupNotFoundException;
import com.project.pet.exceptions.ScheduleNotFoundException;
import com.project.pet.exceptions.StudentNotFoundException;
import com.project.pet.mapper.GroupMapper;
import com.project.pet.mapper.ScheduleMapper;
import com.project.pet.repository.GroupRepo;
import com.project.pet.repository.ScheduleRepo;
import com.project.pet.repository.StudentRepo;
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
class GroupServiceTest {

    @Mock
    private GroupRepo groupRepo;
    @Mock
    private StudentRepo studentRepo;
    @Mock
    private GroupMapper groupMapper;
    @Mock
    private ScheduleRepo scheduleRepo;
    @Mock
    private ScheduleMapper scheduleMapper;

    @InjectMocks
    private GroupService groupService;

    private UUID groupId;
    private UUID studentId;
    private UUID scheduleId;

    private GroupDTO groupDto;
    private GroupDTO outGroupDto;
    private GroupDTO updatedGroupDto;

    private GroupEntity groupEntity;
    private GroupEntity savedGroupEntity;

    private StudentEntity studentEntity;

    private ScheduleEntity scheduleEntity;
    private ScheduleEntity scheduleEntity1;
    private ScheduleEntity savedScheduleEntity;
    private ScheduleEntity incorrectScheduleEntity;

    private ScheduleDTO scheduleDto;
    private ScheduleDTO scheduleDto1;

    private LocalDateTime scheduleStartTime;
    private LocalDateTime scheduleEndTime;
    private LocalDateTime updatedStartLessonTime;
    private LocalDateTime updatedEndLessonTime;

    @BeforeEach
    void setUp() {
        groupId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        studentId = UUID.fromString("321e4567-e89b-12d3-a456-426614174000");
        scheduleId = UUID.fromString("231e4567-e89b-12d3-a456-426614174000");
        UUID scheduleId1 = UUID.fromString("231e4567-e89b-12d3-a456-42661417401");

        scheduleStartTime = LocalDateTime.of(2026, 11, 18, 17, 44, 0, 0);
        scheduleEndTime = scheduleStartTime.plusDays(1);

        groupEntity = new GroupEntity();
        groupEntity.setGroupName("A101");

        savedGroupEntity = new GroupEntity();
        savedGroupEntity.setId(groupId);
        savedGroupEntity.setGroupName(groupEntity.getGroupName());

        groupDto = new GroupDTO(null, "A101");

        outGroupDto = new GroupDTO(savedGroupEntity.getId(), "A101");

        updatedGroupDto = new GroupDTO(groupId, "A102");

        studentEntity = new StudentEntity();
        studentEntity.setStudentName("Ivan");
        studentEntity.setStudentSurname("Ivanov");

        StudentEntity savedStudentEntity = new StudentEntity(); //возможно не усиользууется в тестах, удалить, если так
        savedStudentEntity.setId(studentId);
        savedStudentEntity.setStudentSurname(studentEntity.getStudentSurname());
        savedStudentEntity.setStudentName(studentEntity.getStudentName());

        scheduleEntity = new ScheduleEntity();
        scheduleEntity.setGroup(savedGroupEntity);
        scheduleEntity.setDateStartLesson(scheduleStartTime);
        scheduleEntity.setDateEndLesson(scheduleEntity.getDateStartLesson().plusDays(1));

        savedScheduleEntity = new ScheduleEntity();
        savedScheduleEntity.setId(scheduleId);
        savedScheduleEntity.setGroup(scheduleEntity.getGroup());
        savedScheduleEntity.setDateStartLesson(scheduleEntity.getDateStartLesson());
        savedScheduleEntity.setDateEndLesson(scheduleEntity.getDateEndLesson());

        incorrectScheduleEntity = new ScheduleEntity();
        incorrectScheduleEntity.setId(scheduleId);
        incorrectScheduleEntity.setGroup(new GroupEntity(UUID.fromString("231e4567-e89b-12d3-a456-426614174010"), "A103", null));
        incorrectScheduleEntity.setDateStartLesson(scheduleEntity.getDateStartLesson());
        incorrectScheduleEntity.setDateEndLesson(scheduleEntity.getDateEndLesson());

        scheduleEntity1 = new ScheduleEntity();
        scheduleEntity1.setGroup(new GroupEntity(UUID.fromString("231e4567-e89b-12d3-a456-426614174011"), "A106", null));
        scheduleEntity1.setDateStartLesson(scheduleStartTime);
        scheduleEntity1.setDateEndLesson(scheduleEndTime);

        scheduleDto = new ScheduleDTO(scheduleId, null, null, scheduleStartTime, scheduleEndTime);
        scheduleDto1 = new ScheduleDTO(scheduleId1, null, null, scheduleStartTime, scheduleEndTime);

        updatedStartLessonTime = scheduleStartTime.plusDays(1);
        updatedEndLessonTime = scheduleEndTime.plusDays(1);

    }

    @Test
    void addGroup() {

        when(groupMapper.toEntity(groupDto)).thenReturn(groupEntity);

        when(groupRepo.save(groupEntity)).thenReturn(savedGroupEntity);

        when(groupMapper.toDto(savedGroupEntity)).thenReturn(outGroupDto);

        GroupDTO actual = groupService.addGroup(groupDto);

        verify(groupMapper, times(1)).toEntity(groupDto);
        verify(groupRepo, times(1)).save(groupEntity);
        verify(groupMapper, times(1)).toDto(savedGroupEntity);

        assertSame(outGroupDto, actual);
        assertEquals(savedGroupEntity.getId(), actual.id());
    }

    @Test
    void updateGroup() {

        when(groupRepo.findById(groupId)).thenReturn(Optional.of(groupEntity));

        when(groupRepo.save(groupEntity)).thenReturn(savedGroupEntity);

        when(groupMapper.toDto(savedGroupEntity)).thenReturn(updatedGroupDto);

        GroupDTO actual = groupService.updateGroup(groupId, groupDto);

        assertSame(updatedGroupDto, actual);
        assertEquals(savedGroupEntity.getId(), actual.id());
        assertEquals(updatedGroupDto.groupName(), actual.groupName());
    }

    @Test
    @DisplayName("When update group and group not found then return GroupNotFoundException")
    void whenUpdateGroupAndGroupNotFoundThenReturnGroupNotFoundException() {
        when(groupRepo.findById(groupId)).thenReturn(Optional.empty());

        assertThrows(GroupNotFoundException.class, () -> groupService.updateGroup(groupId, groupDto));
    }

    @Test
    void deleteGroupById() {
        groupService.deleteGroupById(groupId);

        verify(groupRepo, times(1)).deleteById(groupId);
    }

    @Test
    void addStudentToGroup() {

        GroupEntity groupSpy = spy(groupEntity);

        when(studentRepo.findById(studentId)).thenReturn(Optional.of(studentEntity));

        when(groupRepo.findById(groupId)).thenReturn(Optional.of(groupSpy));

        when(groupRepo.save(groupSpy)).thenReturn(groupSpy);

        groupService.addStudentToGroup(studentId, groupId);

        verify(groupSpy, times(1)).addStudent(studentEntity);
        verify(groupRepo, times(1)).save(groupSpy);

        assertTrue(groupSpy.getStudentsInGroup().contains(studentEntity));


    }

    @Test
    @DisplayName("When add student in group and student not found than throw StudentNotFoundException")
    void whenAddStudentInGroupAndStudentNotFoundThanThrowStudentNotFoundException() {
        when(studentRepo.findById(studentId)).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> groupService.addStudentToGroup(studentId, groupId));
    }

    @Test
    @DisplayName("When add student and group not found than throw GroupNotFoundException")
    void whenAddStudentAndGroupNotFoundThanThrowGroupNotFoundException() {
        when(studentRepo.findById(studentId)).thenReturn(Optional.of(studentEntity));

        when(groupRepo.findById(groupId)).thenReturn(Optional.empty());

        assertThrows(GroupNotFoundException.class, () -> groupService.addStudentToGroup(studentId, groupId));
    }

    @Test
    void addTimeOfLessonForGroup() {
        when(scheduleRepo.findById(scheduleId)).thenReturn(Optional.of(scheduleEntity));

        when(scheduleRepo.save(scheduleEntity)).thenReturn(savedScheduleEntity);

        groupService.addTimeOfLessonForGroup(groupId, scheduleId, scheduleStartTime, scheduleEndTime);

        verify(scheduleRepo, times(1)).save(scheduleEntity);

        assertEquals(scheduleEntity.getGroup().getId(), savedScheduleEntity.getGroup().getId());
        assertEquals(scheduleEntity.getDateStartLesson(), savedScheduleEntity.getDateStartLesson());
        assertEquals(scheduleEntity.getDateEndLesson(), savedScheduleEntity.getDateEndLesson());

    }

    @Test
    @DisplayName("When add time of lesson for group and group not found than throw GroupNotFoundException")
    void whenAddTimeOfLessonForGroupAndGroupNotFoundThanThrowGroupNotFoundException() {
        when(scheduleRepo.findById(scheduleId)).thenReturn(Optional.of(incorrectScheduleEntity));

        assertNotEquals(scheduleEntity.getGroup().getId(), incorrectScheduleEntity.getGroup().getId());
        assertThrows(GroupNotFoundException.class, () -> groupService.addTimeOfLessonForGroup(groupId, scheduleId, scheduleStartTime, scheduleEndTime));
    }

    @Test
    @DisplayName("when add time of lesson for group and schedule not found then throw ScheduleNotFoundException")
    void whenAddTimeOfLessonForGroupAndScheduleNotFoundThenThrowScheduleNotFoundException() {
        when(scheduleRepo.findById(scheduleId)).thenReturn(Optional.empty());

        assertThrows(ScheduleNotFoundException.class, () -> groupService.addTimeOfLessonForGroup(groupId, scheduleId, scheduleStartTime, scheduleEndTime));
    }

    @Test
    void viewScheduleForGroup() {
        List<ScheduleEntity> scheduleEntities = List.of(
                scheduleEntity,
                scheduleEntity1);

        PageRequest pageable = PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "dateStartLesson"));

        int totalElementsOnPage = 5;
        Page<@NotNull ScheduleEntity> schedulePage = new PageImpl<>(scheduleEntities, pageable, totalElementsOnPage);

        when(scheduleRepo.viewCourseScheduleForGroup(groupId, pageable)).thenReturn(schedulePage);

        when(scheduleMapper.toDto(scheduleEntity)).thenReturn(scheduleDto);
        when(scheduleMapper.toDto(scheduleEntity1)).thenReturn(scheduleDto1);

        Page<@NotNull ScheduleDTO> actual = groupService.viewScheduleForGroup(groupId, 0, 2);

        verify(scheduleRepo, times(1)).viewCourseScheduleForGroup(groupId, pageable);

        assertEquals(2, actual.getContent().size());
        assertEquals(5, actual.getTotalElements());
        assertEquals(3, actual.getTotalPages());
        assertEquals(2, actual.getSize());

        assertEquals(scheduleDto, actual.getContent().get(0));
        assertEquals(scheduleDto1, actual.getContent().get(1));

    }

    @Test
    void updateCourseTimeForGroup() {


        int countUpdatedRows = 2;
        when(scheduleRepo.updateDateLessonForGroup(groupId, updatedStartLessonTime, updatedEndLessonTime)).thenReturn(countUpdatedRows);

        int actual = scheduleRepo.updateDateLessonForGroup(groupId, updatedStartLessonTime, updatedEndLessonTime);

        verify(scheduleRepo, times(1)).updateDateLessonForGroup(groupId, updatedStartLessonTime, updatedEndLessonTime);

        assertEquals(2, actual);

    }

    @Test
    void deleteScheduleForGroup() {

        when(scheduleRepo.deleteDateLessonForGroup(groupId)).thenReturn(1);

        int actual = scheduleRepo.deleteDateLessonForGroup(groupId);

        verify(scheduleRepo, times(1)).deleteDateLessonForGroup(groupId);

        assertEquals(1, actual);

    }
}