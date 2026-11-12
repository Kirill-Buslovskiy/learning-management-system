package com.project.pet.mapper;

import com.project.pet.dto.group.GroupDTO;
import com.project.pet.entity.group.GroupEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupDTO toDto(GroupEntity entity);

    GroupEntity toEntity(GroupDTO dto);
}
