package com.example.demo.repositories;

import com.example.demo.domain.Actor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActorRepository extends MongoRepository<Actor, String> {
}
