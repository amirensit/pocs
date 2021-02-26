package org.sid.web;

import org.sid.dao.SocietieRepository;
import org.sid.dao.TransactionRepository;
import org.sid.entities.Societie;
import org.sid.entities.Transaction;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

@RestController
public class TransactionReactiveController {

    private final TransactionRepository transactionRepository;

    private final SocietieRepository societieRepository;

    public TransactionReactiveController(TransactionRepository transactionRepository, SocietieRepository societieRepository) {
        this.transactionRepository = transactionRepository;
        this.societieRepository = societieRepository;
    }

    @GetMapping("/transactions")
    public Flux<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @GetMapping("/transactions/{id}")
    public Mono<Transaction> findOne(@PathVariable String id) {
        return transactionRepository.findById(id)
                .flatMap(transaction ->
                        Mono.just(transaction).zipWith(societieRepository.findById(transaction.getSocietieId()),
                                (t, s) -> {
                            t.setS
                                }
                        ));
    }

    @PostMapping("/transactions")
    public Mono<Transaction> save(@RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @DeleteMapping("/transactions/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return transactionRepository.deleteById(id);
    }

    @PutMapping("/transactions/{id}")
    public Mono<Transaction> update(@PathVariable String id, @RequestBody Transaction transaction) {
        transaction.setId(id);
        return transactionRepository.save(transaction);
    }

    @GetMapping(value = "/streamTransactions", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Transaction> streamTransactions() {
        return transactionRepository.findAll();
    }

    @GetMapping(value = "/transactionsBySc")
    public Flux<Transaction> transactionsBySociete(@PathVariable String id) {
        Societie societie = new Societie();
        societie.setId(id);
        return transactionRepository.findBySocietie(societie);
    }

    @GetMapping(value = "/streamTransactionsBySc", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Transaction> streamTransactionsBySociete(@PathVariable String id) {
        return societieRepository.findById(id).flatMapMany(societie -> {
            Flux<Long> interval = Flux.interval(Duration.ofMillis(1000));
            Flux<Transaction> transactionFlux = Flux.fromStream(Stream.generate(() -> {
                Transaction transaction = new Transaction();
                transaction.setInstant(Instant.now());
                transaction.setSocietie(societie);
                transaction.setPrice(3.5);
                return transaction;
            }));
            return Flux.zip(interval, transactionFlux)
                    .map(data -> data.getT2());
        });
    }
}
