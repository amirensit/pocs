package fr.ntech.jwtspringsec.web;

import fr.ntech.jwtspringsec.entities.AppUser;
import fr.ntech.jwtspringsec.service.AccountService;
import fr.ntech.jwtspringsec.web.util.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public AppUser register(@RequestBody RegisterForm registerForm) {
        if (!registerForm.getPassword().equals(registerForm.getRepassword()))
            throw new RuntimeException("you must confirm password");
        AppUser user = accountService.findUserByUsername(registerForm.getUsername());
        if (user != null) throw  new RuntimeException("this user already exist");
        AppUser appUser = new AppUser();
        appUser.setUsername(registerForm.getUsername());
        appUser.setPassword(registerForm.getPassword());
         accountService.saveUser(user);
         accountService.addRoleToUser(registerForm.getUsername(), "USER");
         return appUser;
    }
}
