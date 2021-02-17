package org.sid.authservice.sec.repositories;

import org.sid.authservice.sec.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

    public AppRole findByRoleName(String roleName);
}
