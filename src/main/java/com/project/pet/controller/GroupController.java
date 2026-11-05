package com.project.pet.controller;

import com.project.pet.dto.group.GroupDTO;
import com.project.pet.dto.schedule.ScheduleDTO;
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


    @PostMapping("/groups")
    @Operation(summary = "Add group on site")
    public GroupDTO addGroup(@RequestBody GroupDTO groupDTO) {
        return groupService.addGroup(groupDTO);
    }

    @PostMapping("/groups/{id}")
    @Operation(summary = "Update group by id")
    public GroupDTO updateGroup(@PathVariable @Parameter(name = "groupId") UUID id,
                                @RequestBody GroupDTO groupDTO) {
        return groupService.updateGroup(id, groupDTO);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete group by id")
    public void deleteGroup(@PathVariable @Parameter(name = "groupId") UUID id) {
        groupService.deleteGroupById(id);
    }

    @PostMapping("/groups/{groupId}/student/{studentId}")
    @Operation(summary = "Add student with id to group by id")
    public void addStudentToGroup(@PathVariable @Parameter(name = "studentId") UUID studentId,
                                  @PathVariable @Parameter(name = "groupId") UUID groupId) {
        groupService.addStudentToGroup(studentId, groupId);
    }

    @PostMapping("/groups/{groupId}/schedule/{scheduleId}")
    @Operation(summary = "Add schedule lesson for group")
    public void addTimeOfLessonForGroup(@PathVariable @Parameter(name = "groupId") UUID groupId,
                                        @PathVariable @Parameter(name = "groupId") UUID scheduleId,
                                        @RequestBody LocalDateTime startLesson,
                                        @RequestBody LocalDateTime endLesson) {

        groupService.addTimeOfLessonForGroup(groupId, scheduleId, startLesson, endLesson);
    }

    @GetMapping("/schedule/{id}")
    @Operation(summary = "Get view schedule of group by id")
    public Page<ScheduleDTO> viewScheduleOfGroup(@PathVariable @Parameter(name = "groupId") UUID id) {

        return groupService.viewScheduleForGroup(id, 0, 50);
    }

    @PostMapping("/schedule-for-group/{id}")
    @Operation(summary = "Update course schedule for group by groupId")
    public int updateCourseTimeForGroup(@PathVariable @Parameter(name = "groupId") UUID id,
                                        @RequestBody LocalDateTime startLesson,
                                        @RequestBody LocalDateTime endLesson) {

        return groupService.updateCourseTimeForGroup(id, startLesson, endLesson);
    }

    @DeleteMapping("/delete-schedule/{id}")
    @Operation(summary = "Delete course schedule for group by groupId")
    public int deleteCourseScheduleForGroup(@PathVariable @Parameter(name = "groupId") UUID id) {

        return groupService.deleteScheduleForGroup(id);
    }
}
