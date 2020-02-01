package fr.ntech.jwtspringsec.service;

import fr.ntech.jwtspringsec.entities.AppRole;
import fr.ntech.jwtspringsec.entities.AppUser;
import fr.ntech.jwtspringsec.repositories.RoleRepository;
import fr.ntech.jwtspringsec.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public AccountServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public AppUser saveUser(AppUser appUser) {
        String hashPassword = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(hashPassword);

        return userRepository.save(appUser);
    }

    @Override
    public AppRole saveRole(AppRole appRole) {
        return roleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppRole appRole = roleRepository.findByRoleName(roleName);
        AppUser appUser = userRepository.findByUsername(username);
        appUser.getRoles().add(appRole);
        userRepository.save(appUser);

    }

    @Override
    public AppUser findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
