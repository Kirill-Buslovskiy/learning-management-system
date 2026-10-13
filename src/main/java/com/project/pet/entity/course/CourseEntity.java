package com.project.pet.entity.course;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "course_id")
    private UUID id;

    @Column(name = "course_name")
    private String courseName;

    private String description;

}
