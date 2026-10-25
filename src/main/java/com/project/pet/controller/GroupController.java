package com.project.pet.controller;

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
