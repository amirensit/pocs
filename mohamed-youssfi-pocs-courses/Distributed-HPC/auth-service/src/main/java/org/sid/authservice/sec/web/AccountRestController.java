package org.sid.authservice.sec.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.sid.authservice.sec.JwtUtil;
import org.sid.authservice.sec.entities.AppRole;
import org.sid.authservice.sec.entities.AppUser;
import org.sid.authservice.sec.service.AccountService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class AccountRestController {

    private final AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/users")
    public List<AppUser> getAppUsers() {
        return accountService.getUsers();
    }

    @PostMapping(path = "/users")
    public AppUser saveUser(@RequestBody AppUser appUser) {
        return accountService.addNewUser(appUser);
    }

    @PostMapping(path = "/roles")
    public AppRole saveRole(@RequestBody AppRole appRole) {
        return accountService.addNewRole(appRole);
    }

    @PostMapping(path = "/addRoleToUser")
    public void addRoleToUser(RoleUserForm roleUserForm) {
        accountService.addRoleToUser(roleUserForm.getUserName(), roleUserForm.getRoleName());
    }

    @GetMapping(path = "/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String authToken = request.getHeader(JwtUtil.AUTHORIZATION_HEADER);
        if (authToken != null && authToken.startsWith(JwtUtil.PREFIX)) {
            try {
                String refreshToken = authToken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256(JwtUtil.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                AppUser appUser = accountService.loadUserByUserName(username);
                String jwtAccessToken = JWT.create()
                        .withSubject(appUser.getUserName())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtUtil.EXPIRE_ACCESS_TOKEN))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", appUser.getAppRoles().stream()
                                .map(AppRole::getRoleName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> idToken = new HashMap<>();
                idToken.put("access-token", jwtAccessToken);
                idToken.put("refresh-token", refreshToken); // the same refresh token as in the input
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);
            } catch (Exception e) {
                throw e;
            }
        } else {
            throw new RuntimeException("refresh token required");
        }
    }

    @GetMapping(path = "/profile")
    public AppUser profile(Principal principal) { // current connected user details
        return accountService.loadUserByUserName(principal.getName());
    }
    @Data
    public static class RoleUserForm {
        String userName;
        String roleName;
    }
}
