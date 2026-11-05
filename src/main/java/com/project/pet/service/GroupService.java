package com.project.pet.service;

import com.project.pet.dto.group.GroupDTO;
import com.project.pet.dto.schedule.ScheduleDTO;
import com.project.pet.dto.student.StudentDTO;
import com.project.pet.entity.group.GroupEntity;
import com.project.pet.entity.schedule.ScheduleEntity;
import com.project.pet.entity.student.StudentEntity;
import com.project.pet.exceptions.GroupNotFoundException;
import com.project.pet.exceptions.ScheduleNotFoundException;
import com.project.pet.exceptions.StudentNotFoundException;
import com.project.pet.mapper.GroupMapper;
import com.project.pet.mapper.ScheduleMapper;
import com.project.pet.mapper.StudentMapper;
import com.project.pet.repository.GroupRepo;
import com.project.pet.repository.ScheduleRepo;
import com.project.pet.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepo groupRepo;
    private final StudentRepo studentRepo;
    private final GroupMapper groupMapper;
    private final ScheduleRepo scheduleRepo;
    private final StudentMapper studentMapper;
    private final ScheduleMapper scheduleMapper;

    @Transactional
    public GroupDTO addGroup(GroupDTO groupDTO) {

        GroupEntity entity = groupMapper.toEntity(groupDTO);

        groupRepo.save(entity);

        return groupMapper.toDto(entity);

    }

    @Transactional
    public GroupDTO updateGroup(UUID groupId, GroupDTO groupDTO) {
        GroupEntity group = groupMapper.toEntity(getGroupEntity(groupId));

        group.setGroupName(groupDTO.groupName());

        groupRepo.save(group);
        return groupMapper.toDto(group);
    }

    @Transactional
    public void deleteGroupById(UUID id) {
        groupRepo.deleteById(id);
    }

    @Transactional
    public void addStudentToGroup(UUID studentId, UUID groupId) {
        StudentEntity student = studentMapper.toEntity(getStudent(studentId));

        GroupEntity group = groupMapper.toEntity(getGroupEntity(groupId));

        group.addStudent(student);

        groupRepo.save(group);
    }

    @Transactional
    public void addTimeOfLessonForGroup(UUID groupId, UUID scheduleId, LocalDateTime startLesson, LocalDateTime endLesson) {
        GroupEntity group = groupMapper.toEntity(getGroupEntity(groupId));

        ScheduleEntity schedule = scheduleMapper.toEntity(getSchedule(scheduleId));

        schedule.setDateStartLesson(startLesson);
        schedule.setDateStartLesson(endLesson);

        group.addSchedule(schedule);

        scheduleRepo.save(schedule);
        groupRepo.save(group);
    }

    @Transactional
    public Page<ScheduleDTO> viewScheduleForGroup(UUID groupId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("dateStartLesson").ascending());

        return scheduleRepo.viewCourseScheduleForGroup(groupId, pageable);
    }

    @Transactional
    public ScheduleDTO updateCourseTimeForGroup(UUID groupId, LocalDateTime startLesson, LocalDateTime endLesson) {
        return scheduleRepo.updateDateLessonForGroup(groupId, startLesson, endLesson);
    }

    @Transactional
    public int deleteScheduleForGroup(UUID groupId) {
        return scheduleRepo.deleteDateLessonForGroup(groupId);
    }

    private GroupDTO getGroupEntity(UUID groupId) {
        return groupMapper.toDto(groupRepo.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException("Course with id: " + groupId + " is not found")));
    }

    private StudentDTO getStudent(UUID studentId) {
        return studentMapper.toDto(studentRepo.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student with id: " + studentId + " not found.")));
    }

    private ScheduleDTO getSchedule(UUID scheduleId) {
        return scheduleMapper.toDto(scheduleRepo.findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule with id: " + scheduleId + " is not found")));
    }
}
