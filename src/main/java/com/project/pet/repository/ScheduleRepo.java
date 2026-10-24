package com.project.pet.repository;

import com.project.pet.entity.schedule.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface ScheduleRepo extends JpaRepository<ScheduleEntity, UUID> {

    @Query(value = "SELECT schedule FROM ScheduleEntity schedule JOIN FETCH schedule.course.teacher.id")
    ScheduleEntity viewClassScheduleForTeacher(UUID teacherId);

    @Query(value = "SELECT schedule FROM ScheduleEntity schedule JOIN FETCH schedule.group.id")
    ScheduleEntity viewCourseScheduleForGroup(UUID groupId);

    @Query(value = "UPDATE ScheduleEntity se SET se.dateStartLesson = :startLesson, se.dateEndLesson = :endndLesson WHERE se.group.id = :groupId")
    ScheduleEntity updateDateLessonForGroup(@Param("groupId") UUID groupId,
                                            @Param("startLesson") LocalDateTime startLesson,
                                            @Param("endLesson") LocalDateTime endLesson);

    @Query(value = "UPDATE FROM ScheduleEntity se SET se.dateStartLesson = NULL, se.dateEndLesson = NULL WHERE se.group.id = :groupId")
    ScheduleEntity deleteDateLessonForGroup(@Param("groupId") UUID groupId);
}
