package fr.ntech.jwtspringsec.repositories;

import fr.ntech.jwtspringsec.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
