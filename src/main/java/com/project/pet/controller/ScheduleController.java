package com.project.pet.controller;

import com.project.pet.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
@Tag(name = "Schedule", description = "Controller of schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @DeleteMapping("/delete")
    @Operation(summary = "Clean schedule after 1 year")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Schedule deleted successfully."),
            @ApiResponse(responseCode = "422", description = "This schedule is less than a year old."),
            @ApiResponse(responseCode = "500", description = "Something wrong on server.")
    })
    public void cleanScheduleAfterYear(@RequestBody LocalDateTime time) {
        scheduleService.deleteScheduledAfterYear(time);
    }
}
