package com.project.pet.repository;

import com.project.pet.entity.course.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepo extends JpaRepository<CourseEntity, UUID> {
}
