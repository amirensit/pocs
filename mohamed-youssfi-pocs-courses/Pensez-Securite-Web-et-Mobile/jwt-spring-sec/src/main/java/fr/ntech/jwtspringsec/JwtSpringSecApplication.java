package fr.ntech.jwtspringsec;

import fr.ntech.jwtspringsec.entities.Task;
import fr.ntech.jwtspringsec.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Stream;

@SpringBootApplication
public class JwtSpringSecApplication implements CommandLineRunner {

	@Autowired
	private TaskRepository taskRepository;

	public static void main(String[] args) {
		SpringApplication.run(JwtSpringSecApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Stream.of("T1", "T2", "T3").forEach( t -> {
			taskRepository.save(new Task(null, t));
		});
		taskRepository.findAll().forEach( t -> {
			System.out.println(t.getTaskName());
		});
	}
}
