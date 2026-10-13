package com.project.pet.entity.schedule;

import com.project.pet.entity.course.CourseEntity;
import com.project.pet.entity.group.GroupEntity;
import com.project.pet.entity.teacher.TeacherEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "schedule")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;


    private Set<GroupEntity> groupSet;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "teacher_id", unique = true)
    private TeacherEntity teacher;


    @OneToOne(mappedBy = "teacher")
    private CourseEntity course;


    private LocalDateTime dateStartLesson;


    private LocalDateTime dateEndLesson;
}
