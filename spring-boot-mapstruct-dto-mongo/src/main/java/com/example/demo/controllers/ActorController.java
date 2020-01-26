package com.example.demo.controllers;

import com.example.demo.services.ActorService;
import com.example.demo.services.dto.ActorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping("/add")
    public ResponseEntity<ActorDTO> saveActor(@RequestBody ActorDTO actorDTO) {
        ActorDTO result = actorService.save(actorDTO);
        return ResponseEntity.ok().body(result);
    }
}
