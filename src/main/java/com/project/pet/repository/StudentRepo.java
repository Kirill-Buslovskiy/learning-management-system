package com.project.pet.repository;

import com.project.pet.entity.student.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepo extends JpaRepository<StudentEntity, UUID> {
}
