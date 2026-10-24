package com.project.pet.entity.course;

import com.project.pet.entity.group.GroupEntity;
import com.project.pet.entity.schedule.ScheduleEntity;
import com.project.pet.entity.teacher.TeacherEntity;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "course_name")
    private String courseName;

    private String description;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private TeacherEntity teacher;

    @ManyToMany
    @JoinTable(name = "groups_in_courses",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
    @ToString.Exclude
    private final Set<GroupEntity> groupOnCourses = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    @ToString.Exclude
private final Set<ScheduleEntity> schedulesForCourses = new HashSet<>();

    public void addGroup(GroupEntity groupEntity) {
        groupOnCourses.add(groupEntity);
        groupEntity.getCourses().add(this);
    }
}
