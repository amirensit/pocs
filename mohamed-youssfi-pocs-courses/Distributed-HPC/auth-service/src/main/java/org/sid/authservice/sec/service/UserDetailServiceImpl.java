package org.sid.authservice.sec.service;

import org.sid.authservice.sec.entities.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    private final AccountService accountService;

    public UserDetailServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = accountService.loadUserByUserName(userName);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        appUser.getAppRoles().stream().forEach(
                appRole -> authorities.add(new SimpleGrantedAuthority(appRole.getRoleName()))
        );
        return new User(appUser.getUserName(), appUser.getPassword(), authorities);
    }
}
