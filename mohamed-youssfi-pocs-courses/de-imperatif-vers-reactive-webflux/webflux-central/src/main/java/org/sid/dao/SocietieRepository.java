package org.sid.dao;

import org.sid.entities.Societie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SocietieRepository extends ReactiveMongoRepository<Societie, String> {
}
