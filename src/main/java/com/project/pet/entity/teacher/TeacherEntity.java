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

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "teacher_name")
    private String firstName;

    @Column(name = "teacher_surname")
    private String surname;

    @OneToMany(mappedBy = "teacher")
    private final Set<CourseEntity> courses = new HashSet<>();

    public void addCourse(CourseEntity course) {
        courses.add(course);
        course.setTeacher(this);
    }
}
