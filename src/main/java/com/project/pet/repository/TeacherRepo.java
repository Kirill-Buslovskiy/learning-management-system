package com.project.pet.repository;

import com.project.pet.entity.teacher.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeacherRepo extends JpaRepository<TeacherEntity, UUID> {
}
