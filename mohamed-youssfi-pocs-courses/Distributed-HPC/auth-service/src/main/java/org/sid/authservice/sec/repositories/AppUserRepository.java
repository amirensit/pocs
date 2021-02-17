package org.sid.authservice.sec.repositories;

import org.sid.authservice.sec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    public AppUser findByUserName(String userName);
}
