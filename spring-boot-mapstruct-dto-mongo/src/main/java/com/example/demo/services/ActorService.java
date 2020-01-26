package com.example.demo.services;

import com.example.demo.domain.Actor;
import com.example.demo.repositories.ActorRepository;
import com.example.demo.services.dto.ActorDTO;
import com.example.demo.services.mapper.ActorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    public ActorService(ActorRepository actorRepository, ActorMapper actorMapper) {
        this.actorRepository = actorRepository;
        this.actorMapper = actorMapper;
    }

    public ActorDTO save(ActorDTO actorDTO) {
        log.warn("actorDTO:  {}", actorDTO);
        Actor actor = actorMapper.toEntity(actorDTO);
        actor = this.actorRepository.save(actor);
        return actorMapper.toDTO(actor);

    }
}
