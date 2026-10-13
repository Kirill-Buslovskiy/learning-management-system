package com.project.pet.entity.group;

import com.project.pet.entity.student.StudentEntity;
import jakarta.persistence.*;
import lombok.*;

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
    private UUID id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_students")
    @ManyToMany(mappedBy = "groups")
    private Set<StudentEntity> studentsInGroup;

}
