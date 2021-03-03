package org.sid.dao;

import org.sid.entities.Societie;
import org.sid.entities.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {

    public Flux<Transaction> findBySocietieId(String societieId);
}
