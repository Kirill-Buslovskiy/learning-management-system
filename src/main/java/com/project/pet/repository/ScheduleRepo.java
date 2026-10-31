package com.project.pet.repository;

import com.project.pet.entity.schedule.ScheduleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface ScheduleRepo extends JpaRepository<ScheduleEntity, UUID> {

    @Query(value = "SELECT schedule FROM ScheduleEntity schedule JOIN FETCH schedule.course course JOIN FETCH  course.teacher teacher WHERE teacher.id = :teacherId",
            countQuery = "SELECT COUNT(schedule) FROM ScheduleEntity schedule WHERE schedule.course.teacher.id = :teacherId")
    Page<ScheduleEntity> viewClassScheduleForTeacher(@Param(value = "teacherId") UUID teacherId, Pageable pageable);

    @Query(value = "SELECT schedule FROM ScheduleEntity schedule JOIN FETCH schedule.group group WHERE group.id = :groupId",
            countQuery = "SELECT COUNT(schedule) FROM ScheduleEntity schedule WHERE schedule.group.id = :teacherId")
    Page<ScheduleEntity> viewCourseScheduleForGroup(@Param("groupId") UUID groupId, Pageable pageable);

    @Query(value = "UPDATE ScheduleEntity se SET se.dateStartLesson = :startLesson, se.dateEndLesson = :endLesson WHERE se.group.id = :groupId")
    @Modifying
    ScheduleEntity updateDateLessonForGroup(@Param("groupId") UUID groupId,
                                            @Param("startLesson") LocalDateTime startLesson,
                                            @Param("endLesson") LocalDateTime endLesson);

    @Query(value = "UPDATE ScheduleEntity se SET se.dateStartLesson = NULL, se.dateEndLesson = NULL WHERE se.group.id = :groupId")
    @Modifying
    int deleteDateLessonForGroup(@Param("groupId") UUID groupId);


    @Query(value = "DELETE FROM ScheduleEntity se WHERE se.dateEndLesson > :now")
    @Modifying
    void cleanScheduleAfterYear(@Param("now") LocalDateTime now);
}
