package fr.ntech.ntechsec.service;

import fr.ntech.ntechsec.entities.Role;
import fr.ntech.ntechsec.entities.User;
import fr.ntech.ntechsec.repositories.RoleRepository;
import fr.ntech.ntechsec.repositories.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("hasRole(\"" + "ADMIN"+"\")")
public class UserRestService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserRestService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @RequestMapping(value = "/addUser")
    public User save(User user) {
        return userRepository.save(user);
    }

    @RequestMapping(value = "/findUsers")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/addRole")
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @RequestMapping(value = "/findRoles")
    public List<Role> findRoles() {
        return roleRepository.findAll();
    }

    @RequestMapping(value = "/addRoleToUser")
    public User addRoleToUser(String username, String role) {
        User user = userRepository.findOneByUsername(username);
        Role r = roleRepository.findOneByRole(role);
        user.getRoles().add(r);
        return userRepository.save(user);
    }

}
