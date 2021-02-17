package org.sid.authservice.sec.service;

import org.sid.authservice.sec.entities.AppRole;
import org.sid.authservice.sec.entities.AppUser;

import java.util.List;

public interface AccountService {

    public AppUser addNewUser(AppUser appUser);

    public AppRole addNewRole(AppRole appRole);

    public void addRoleToUser(String userName, String roleName);

    public AppUser loadUserByUserName(String userName);

    public List<AppUser> getUsers();
}
