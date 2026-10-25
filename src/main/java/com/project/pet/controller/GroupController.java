package com.project.pet.controller;

import com.project.pet.entity.dto.group.GroupDTO;
import com.project.pet.entity.group.GroupEntity;
import com.project.pet.entity.schedule.ScheduleEntity;
import com.project.pet.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;


    @PutMapping("/add")
    public GroupEntity addGroup(@RequestBody GroupDTO groupDTO) {
        return groupService.addGroup(groupDTO);
    }

    @PutMapping("/update/{groupId}")
    public GroupEntity updateGroup(@PathVariable UUID groupId, @RequestBody GroupDTO groupDTO) {
        return groupService.updateGroup(groupId, groupDTO);
    }

    @PutMapping("/delete/{groupId}")
    public void deleteGroup(@PathVariable UUID groupId) {
        groupService.deleteGroupById(groupId);
    }

    @PutMapping("/add/{groupId}/student/{studentId}")
    public void addStudentToGroup(@PathVariable UUID studentId,
                                  @PathVariable UUID groupId) {
        groupService.addStudentToGroup(studentId, groupId);
    }

    @PutMapping("/add-time-lesson/{groupId}/schedule/{scheduleId}")
    public void addTimeOfLessonForGroup(@PathVariable UUID groupId,
                                        @PathVariable UUID scheduleId,
                                        @RequestBody LocalDateTime startLesson,
                                        @RequestBody LocalDateTime endLesson) {

        groupService.addTimeOfLessonForGroup(groupId, scheduleId, startLesson, endLesson);
    }

    @GetMapping("/view-schedule-of-group/{groupId}")
    public ScheduleEntity viewScheduleOfGroup(@PathVariable UUID groupId) {

        return groupService.viewScheduleForGroup(groupId);
    }

    @PutMapping("/update-course-time-of-group/{groupId}")
    public ScheduleEntity updateCourseTimeForGroup(@PathVariable UUID groupId,
                                                   @RequestBody LocalDateTime startLesson,
                                                   @RequestBody LocalDateTime endLesson) {

        return groupService.updateCourseTimeForGroup(groupId, startLesson, endLesson);
    }

    @PutMapping("/delete-course-schedule-for-group/{groupId}")
    public void deleteCourseScheduleForGroup(@PathVariable UUID groupId) {

        groupService.deleteScheduleForGroup(groupId);
    }
}
