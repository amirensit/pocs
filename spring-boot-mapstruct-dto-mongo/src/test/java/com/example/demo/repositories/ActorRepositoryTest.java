package com.example.demo.repositories;

import com.example.demo.domain.Actor;
import com.example.demo.services.mapper.ActorMapperTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

/*
* We usually don't want to do validation as late as in the persistence layer because it means that the business code above has worked with potentially invalid objects which may lead to unforeseen errors.
 */

// @ExtendWith(SpringExtension.class) this annotation is already included in @DataMongoTest annotation.
@DataMongoTest
public class ActorRepositoryTest {

    private Actor actor;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void setup() {
        this.actor = ActorMapperTest.createActor();
    }

    /*
     * here we are going to use the validation at entity level (the persistence layer level).
     * In contrast to request body validation a failed validation will trigger a ConstraintViolationException instead of a MethodArgumentNotValidException.
     * Spring does not register a default exception handler for this exception, so it will by default cause a response with HTTP status 500
     */
    @Test
    public void whenActorCodeInvalid_thenThrowsException() {
        this.actor.setActorCode("er");
        Actor actor = actorRepository.save(this.actor);
        /*assertThrows(ConstraintViolationException.class, () -> {
            actorRepository.save(actor);
            mongoTemplate.fl
        });*/
    }

}
