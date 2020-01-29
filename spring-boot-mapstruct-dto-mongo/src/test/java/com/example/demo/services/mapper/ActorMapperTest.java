package com.example.demo.services.mapper;

import com.example.demo.domain.Actor;
import com.example.demo.services.dto.ActorDTO;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ActorMapperTest {

    private static final String DEFAULT_ACTOR_CODE = "a0001";

    private static final String DEFAULT_FIRST_NAME = "amir";

    private static final String DEFAULT_LAST_NAME = "choubani";

    ActorMapper actorMapper = ActorMapper.INSTANCE;

    public static Actor createActor() {
        Actor actor = new Actor();
        actor.setActorCode(DEFAULT_ACTOR_CODE);
        actor.setFirstName(DEFAULT_FIRST_NAME);
        actor.setLastName(DEFAULT_LAST_NAME);
        return actor;
    }

    public static ActorDTO createActorDTO() {
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setActorCode(DEFAULT_ACTOR_CODE);
        actorDTO.setFirstName(DEFAULT_FIRST_NAME);
        actorDTO.setLastName(DEFAULT_LAST_NAME);
        return actorDTO;
    }

    @Test
    public void toDtoTest() throws Exception {

        //given
        Actor actor = createActor();

        //when
        ActorDTO actorDTO = actorMapper.toDTO(actor);

        //then
        assertThat(actorDTO.getId()).isEqualTo(actor.getId());
        assertThat(actorDTO.getActorCode()).isEqualTo(actor.getActorCode());
        assertThat(actorDTO.getFirstName()).isEqualTo(actor.getFirstName());
        assertThat(actorDTO.getLastName()).isEqualTo(actor.getLastName());
    }

    @Test
    public void toEntityTest() throws Exception {
        //given
        ActorDTO actorDTO = createActorDTO();

        //when
        Actor actor = actorMapper.toEntity(actorDTO);

        //then. just testing for firstName attribute. Same for other fields.
        assertThat(actor.getFirstName()).isEqualTo(actorDTO.getFirstName());



    }
}
