package org.sid;

import org.sid.dao.SocietieRepository;
import org.sid.dao.TransactionRepository;
import org.sid.entities.Societie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class WebFluxReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxReactiveApplication.class, args);
    }

    @Bean
    CommandLineRunner start(SocietieRepository societieRepository, TransactionRepository transactionRepository) {
        return args -> {
            Stream.of("SG", "TALAN", "ALTRAN", "ASSURANCE").forEach(
                    element -> societieRepository.save(new Societie(element, element, Math.random() * 900))
                    .subscribe(societie -> System.out.println(societie))
            );
        };
    }
}
