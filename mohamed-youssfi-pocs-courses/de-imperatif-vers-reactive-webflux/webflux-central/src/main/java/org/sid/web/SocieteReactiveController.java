package org.sid.web;

import org.sid.dao.SocietieRepository;
import org.sid.entities.Societie;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SocieteReactiveController {

    private final SocietieRepository societieRepository;

    public SocieteReactiveController(SocietieRepository societieRepository) {
        this.societieRepository = societieRepository;
    }

    @GetMapping("/societes")
    public Flux<Societie> findAll() {
        return societieRepository.findAll();
    }

    @GetMapping("/societes/{id}")
    public Mono<Societie> findOne(@PathVariable String id) {
        return societieRepository.findById(id);
    }

    @PostMapping("/societes")
    public Mono<Societie> save(@RequestBody Societie societie) {
        return societieRepository.save(societie);
    }

    @DeleteMapping("/societes/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return societieRepository.deleteById(id);
    }

    @PutMapping("/societes/{id}")
    public Mono<Societie> update(@PathVariable String id, @RequestBody Societie societie) {
        societie.setId(id);
        return societieRepository.save(societie);
    }
}
