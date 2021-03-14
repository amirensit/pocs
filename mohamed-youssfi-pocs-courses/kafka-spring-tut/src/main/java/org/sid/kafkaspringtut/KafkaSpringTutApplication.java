package org.sid.kafkaspringtut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaSpringTutApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaSpringTutApplication.class, args);
	}

}
