package fr.ntech.jwtspringsec.service;

import fr.ntech.jwtspringsec.entities.AppRole;
import fr.ntech.jwtspringsec.entities.AppUser;

public interface AccountService {

    public AppUser saveUser(AppUser appUser);

    public AppRole saveRole(AppRole appRole);

    public void addRoleToUser(String username, String roleName);

    public AppUser findUserByUsername(String username);
}
