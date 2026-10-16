package com.project.pet.entity.group;

import com.project.pet.entity.student.StudentEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String groupName;

    @ManyToMany
    @JoinTable(name = "students_in_group",
    joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    @ToString.Exclude
    private final Set<StudentEntity> studentsInGroup =  new HashSet<>();

}
