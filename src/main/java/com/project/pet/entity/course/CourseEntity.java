package com.project.pet.entity.course;

import com.project.pet.entity.schedule.ScheduleEntity;
import com.project.pet.entity.student.StudentEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "course")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "course_name")
    private String courseName;

    private String description;

    @ManyToMany
    @JoinTable(name = "schedule_of_courses",
    joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "scheduled_id", referencedColumnName = "id"))
    @ToString.Exclude
    private final Set<ScheduleEntity> schedules = new HashSet<>();


}
