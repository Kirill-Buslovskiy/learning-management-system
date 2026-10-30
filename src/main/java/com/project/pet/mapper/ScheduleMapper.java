package com.project.pet.mapper;

import com.project.pet.entity.dto.group.GroupDTO;
import com.project.pet.entity.dto.schedule.ScheduleDTO;
import com.project.pet.entity.schedule.ScheduleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    GroupDTO toDto(ScheduleEntity entity);

    ScheduleEntity toEntity(ScheduleDTO dto);
}
