package com.project.pet.repository;

import com.project.pet.entity.group.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GroupRepo extends JpaRepository<GroupEntity, UUID> {
}
