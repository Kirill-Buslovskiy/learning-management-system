package com.project.pet.entity.schedule;

import com.project.pet.entity.course.CourseEntity;
import com.project.pet.entity.group.GroupEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
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
    @ManyToMany
    @JoinTable(name = "schedule_of_courses",
            joinColumns = @JoinColumn(name = "schedule_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    @ToString.Exclude
    private final Set<CourseEntity> courses = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @ManyToOne
    private GroupEntity group;
    private LocalDateTime dateStartLesson;


    private LocalDateTime dateEndLesson;
}
