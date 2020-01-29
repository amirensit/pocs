package com.example.demo.controllers;

import com.example.demo.services.ActorService;
import com.example.demo.services.dto.ActorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/actors")
public class ActorController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    /*
    BindingResult class does not throw any excpetion. It just intercept the errors.
     */
    @PostMapping("/add")
    public ResponseEntity<ActorDTO> saveActor(@Valid @RequestBody ActorDTO actorDTO/*, BindingResult bindingResult*/) {
        /*if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
        }*/
        ActorDTO result = actorService.save(actorDTO);
        return ResponseEntity.ok().body(result);
    }

}
