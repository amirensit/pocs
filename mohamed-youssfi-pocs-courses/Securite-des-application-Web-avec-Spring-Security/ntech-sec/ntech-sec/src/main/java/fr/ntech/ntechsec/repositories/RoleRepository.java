package fr.ntech.ntechsec.repositories;

import fr.ntech.ntechsec.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findOneByRole(String role);
}
