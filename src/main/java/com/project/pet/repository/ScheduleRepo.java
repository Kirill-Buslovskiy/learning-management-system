package com.project.pet.repository;

import com.project.pet.entity.schedule.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScheduleRepo extends JpaRepository<ScheduleEntity, UUID> {
}
