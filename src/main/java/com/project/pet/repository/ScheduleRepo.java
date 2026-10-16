package com.project.pet.repository;

import com.project.pet.entity.schedule.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScheduleRepo extends JpaRepository<ScheduleEntity, UUID> {
}
