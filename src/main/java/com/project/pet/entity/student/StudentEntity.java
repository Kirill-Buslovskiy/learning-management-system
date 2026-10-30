package com.project.pet.entity.student;

import com.project.pet.entity.group.GroupEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentEntity {
    @ManyToMany
    @JoinTable(
            name = "students_in_group",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id")
    )
    @NotNull(message = "Student must be a member of at least one group!")
    @ToString.Exclude
    private final Set<GroupEntity> groups = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "student_surname")
    private String studentSurname;


}
