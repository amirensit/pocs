package com.example.demo.services.mapper;

import com.example.demo.domain.Actor;
import com.example.demo.services.dto.ActorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ActorMapper {

    ActorMapper INSTANCE = Mappers.getMapper(ActorMapper.class);

    ActorDTO toDTO(Actor actor);

    Actor toEntity(ActorDTO actorDTO);
}
