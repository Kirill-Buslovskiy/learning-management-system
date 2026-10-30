package com.project.pet.entity.schedule;

import com.project.pet.entity.course.CourseEntity;
import com.project.pet.entity.group.GroupEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    @ToString.Exclude
    private GroupEntity group;

    @Column(name = "date_start_lesson")
    private LocalDateTime dateStartLesson;

    @Column(name = "date_end_lesson")
    private LocalDateTime dateEndLesson;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ToString.Exclude
    private CourseEntity course;
}
