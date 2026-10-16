package com.project.pet.mapper;

import com.project.pet.entity.dto.group.GroupDTO;
import com.project.pet.entity.group.GroupEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupEntity toDto(GroupEntity entity);

    GroupEntity toEntity(GroupDTO dto);
}
