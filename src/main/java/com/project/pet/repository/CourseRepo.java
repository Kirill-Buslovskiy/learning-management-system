package com.project.pet.repository;

import com.project.pet.entity.course.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepo extends JpaRepository<CourseEntity, UUID> {
}
