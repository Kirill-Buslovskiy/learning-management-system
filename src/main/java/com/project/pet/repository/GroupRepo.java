package com.project.pet.repository;

import com.project.pet.entity.group.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GroupRepo extends JpaRepository<GroupEntity, UUID> {
}
