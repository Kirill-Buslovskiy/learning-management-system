package com.project.pet.entity.teacher;

import com.project.pet.entity.course.CourseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "teacher")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TeacherEntity {
    @OneToMany
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private final Set<CourseEntity> courses = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "teacher_name")
    private String firstName;
    @Column(name = "teacher_surname")
    private String surname;
}
