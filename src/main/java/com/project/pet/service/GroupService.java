package com.project.pet.service;

import com.project.pet.entity.dto.group.GroupDTO;
import com.project.pet.entity.group.GroupEntity;
import com.project.pet.entity.schedule.ScheduleEntity;
import com.project.pet.entity.student.StudentEntity;
import com.project.pet.exceptions.GroupNotFoundException;
import com.project.pet.exceptions.ScheduleNotFoundException;
import com.project.pet.exceptions.StudentNotFoundException;
import com.project.pet.mapper.GroupMapper;
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

    @Transactional
    public GroupEntity addGroup(GroupDTO groupDTO) {
        return groupRepo
                .save(groupMapper.toEntity(groupDTO));

    }

    @Transactional
    public GroupEntity updateGroup(UUID groupId, GroupDTO groupDTO) {
        GroupEntity group = getGroupEntity(groupId);

        group.setGroupName(groupDTO.groupName());
        return groupRepo.save(group);
    }

    @Transactional
    public void deleteGroupById(UUID id) {
        groupRepo.deleteById(id);
    }

    @Transactional
    public void addStudentToGroup(UUID studentId, UUID groupId) {
        StudentEntity student = getStudent(studentId);

        GroupEntity group = getGroupEntity(groupId);

        group.addStudent(student);

        groupRepo.save(group);
    }

    @Transactional
    public void addTimeOfLessonForGroup(UUID groupId, UUID scheduleId, LocalDateTime startLesson, LocalDateTime endLesson) {
        GroupEntity group = getGroupEntity(groupId);

        ScheduleEntity schedule = getSchedule(scheduleId);

        schedule.setDateStartLesson(startLesson);
        schedule.setDateStartLesson(endLesson);

        scheduleRepo.save(schedule);
        group.addSchedule(schedule);
        groupRepo.save(group);
    }

    @Transactional
    public Page<ScheduleEntity> viewScheduleForGroup(UUID groupId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("dateStartLesson").ascending());

        return scheduleRepo.viewCourseScheduleForGroup(groupId, pageable);
    }

    @Transactional
    public ScheduleEntity updateCourseTimeForGroup(UUID groupId, LocalDateTime startLesson, LocalDateTime endLesson) {
        return scheduleRepo.updateDateLessonForGroup(groupId, startLesson, endLesson);
    }

    @Transactional
    public void deleteScheduleForGroup(UUID groupId) {
        scheduleRepo.deleteDateLessonForGroup(groupId);
    }

    private GroupEntity getGroupEntity(UUID groupId) {
        return groupRepo.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException("Course with id: " + groupId + " is not found"));
    }

    private StudentEntity getStudent(UUID studentId) {
        return studentRepo.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student with id: " + studentId + " not found."));
    }

    private ScheduleEntity getSchedule(UUID scheduleId) {
        return scheduleRepo.findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule with id: " + scheduleId + " is not found"));
    }
}
