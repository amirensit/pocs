package fr.ntech.jwtspringsec;

import fr.ntech.jwtspringsec.entities.AppRole;
import fr.ntech.jwtspringsec.entities.AppUser;
import fr.ntech.jwtspringsec.entities.Task;
import fr.ntech.jwtspringsec.repositories.TaskRepository;
import fr.ntech.jwtspringsec.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Stream;

@SpringBootApplication
public class JwtSpringSecApplication implements CommandLineRunner {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(JwtSpringSecApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

        accountService.saveUser(new AppUser(null, "admin", "1234", null));
        accountService.saveUser(new AppUser(null, "user", "1234", null));
        accountService.saveRole(new AppRole(null, "ADMIN"));
        accountService.saveRole(new AppRole(null, "USER"));
        accountService.addRoleToUser("admin", "ADMIN");
        accountService.addRoleToUser("user", "USER");
		Stream.of("T1", "T2", "T3", "T4", "T5").forEach( t -> {
			taskRepository.save(new Task(null, t));
		});
		taskRepository.findAll().forEach( t -> {
			System.out.println(t.getTaskName());
		});
	}
}
