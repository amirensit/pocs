package fr.ntech.ntechsec.repositories;

import fr.ntech.ntechsec.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findOneByUsername(String username);
}
