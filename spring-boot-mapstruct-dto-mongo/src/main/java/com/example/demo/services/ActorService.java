package com.example.demo.services;

import com.example.demo.domain.Actor;
import com.example.demo.repositories.ActorRepository;
import com.example.demo.services.dto.ActorDTO;
import com.example.demo.services.mapper.ActorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Set;

/*
* Instead of (or additionally to) validating input on the controller level, we can also validate the input to any Spring components.
* In order to to this, we use a combination of the @Validated and @Valid annotations.
 */
@Service
@Validated // Note that we have to add Spring’s @Validated annotation to the service at class level to tell Spring to evaluate the constraint annotations on method parameters
public class ActorService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    private  final Validator validator;

    public ActorService(ActorRepository actorRepository, ActorMapper actorMapper, Validator validator) {
        this.actorRepository = actorRepository;
        this.actorMapper = actorMapper;
        this.validator = validator;
    }

    public ActorDTO save(ActorDTO actorDTO) {
        log.warn("actorDTO:  {}", actorDTO);
        Actor actor = actorMapper.toEntity(actorDTO);
        actor = this.actorRepository.save(actor);
        return actorMapper.toDTO(actor);

    }

    /*
    * This method is used to validate constraints in service layer.
    * This will trigger an exception that will not be translated by `DefaultHandlerExceptionResolver`.
    * At integration test, we can expect exceptions. This is not the case in controllers layer.
     */
    public ActorDTO validateAndSave(@Valid ActorDTO actorDTO) {
        log.warn("actorDTO:  {}", actorDTO);
        Actor actor = actorMapper.toEntity(actorDTO);
        actor = this.actorRepository.save(actor);
        return actorMapper.toDTO(actor);

    }
    /*
    * There may be cases when we want to invoke validation programmatically instead of relying on Spring’s built-in Bean Validation support.
    * See https://reflectoring.io/bean-validation-with-spring-boot/#validating-programmatically.
    * Here we don't add @Valid in parameter because we are triggering validation manually !
     */
    public void programmaticallyValidate(ActorDTO actorDTO) {
        Set<ConstraintViolation<ActorDTO>> violations = validator.validate(actorDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }


}
