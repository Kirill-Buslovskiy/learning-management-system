package com.project.pet.controller;

import com.project.pet.dto.group.GroupDTO;
import com.project.pet.entity.group.GroupEntity;
import com.project.pet.entity.schedule.ScheduleEntity;
import com.project.pet.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/group")
@Tag(name = "Groups", description = "Control of group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;


    @PutMapping("/add")
    @Operation(summary = "Add group on site")
    public GroupEntity addGroup(@RequestBody GroupDTO groupDTO) {
        return groupService.addGroup(groupDTO);
    }

    @PutMapping("/update/{groupId}")
    @Operation(summary = "Update group by id")
    public GroupEntity updateGroup(@PathVariable @Parameter(name = "groupId") UUID groupId,
                                   @RequestBody GroupDTO groupDTO) {
        return groupService.updateGroup(groupId, groupDTO);
    }

    @PutMapping("/delete/{groupId}")
    @Operation(summary = "Delete group by id")
    public void deleteGroup(@PathVariable @Parameter(name = "groupId") UUID groupId) {
        groupService.deleteGroupById(groupId);
    }

    @PutMapping("/add/{groupId}/student/{studentId}")
    @Operation(summary = "Add student with id to group by id")
    public void addStudentToGroup(@PathVariable @Parameter(name = "studentId") UUID studentId,
                                  @PathVariable @Parameter(name = "groupId") UUID groupId) {
        groupService.addStudentToGroup(studentId, groupId);
    }

    @PutMapping("/add-time-lesson/{groupId}/schedule/{scheduleId}")
    @Operation(summary = "Add schedule lesson for group")
    public void addTimeOfLessonForGroup(@PathVariable @Parameter(name = "groupId") UUID groupId,
                                        @PathVariable @Parameter(name = "groupId") UUID scheduleId,
                                        @RequestBody LocalDateTime startLesson,
                                        @RequestBody LocalDateTime endLesson) {

        groupService.addTimeOfLessonForGroup(groupId, scheduleId, startLesson, endLesson);
    }

    @GetMapping("/view-schedule-of-group/{groupId}")
    @Operation(summary = "Get view schedule of group by id")
    public Page<ScheduleEntity> viewScheduleOfGroup(@PathVariable @Parameter(name = "groupId") UUID groupId) {

        return groupService.viewScheduleForGroup(groupId, 0, 50);
    }

    @PutMapping("/update-course-time-of-group/{groupId}")
    @Operation(summary = "Update course schedule for group by groupId")
    public ScheduleEntity updateCourseTimeForGroup(@PathVariable @Parameter(name = "groupId") UUID groupId,
                                                   @RequestBody LocalDateTime startLesson,
                                                   @RequestBody LocalDateTime endLesson) {

        return groupService.updateCourseTimeForGroup(groupId, startLesson, endLesson);
    }

    @PutMapping("/delete-course-schedule-for-group/{groupId}")
    @Operation(summary = "Delete course schedule for group by groupId")
    public void deleteCourseScheduleForGroup(@PathVariable @Parameter(name = "groupId") UUID groupId) {

        groupService.deleteScheduleForGroup(groupId);
    }
}
