package fr.ntech.ntechsec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
// Spring security strategy to secure methods. Methods must have @secure(value={'role name'}) annotation.
// you must set the field securedEnabled to true in order to use @secured annotation.
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired // here we use dependency injection to inject AuthenticationManagerBuilder and DataSource.
    public void globalConfig(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
        /*auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}123").roles("ADMIN", "PROF");
        auth.inMemoryAuthentication()
                .withUser("prof").password("{noop}123").roles("PROF");
        auth.inMemoryAuthentication()
                .withUser("et1").password("{noop}123").roles("ETUDIANT");
        auth.inMemoryAuthentication()
                .withUser("sco1").password("{noop}123").roles("SCOLARITE");*/
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                // this method will sets the query to be used for finding a user by their username.
                .usersByUsernameQuery("select username, password, true from users where username = ?")
                // I tested without 'as principal and as role'  and it works.
                .authoritiesByUsernameQuery("select user_username as principal, roles_role as role from users_roles where user_username = ?");
                //.rolePrefix("ROLE_"); I tested this and it works either we set ROLE_ prefix or not maybe because spring security always add this prefix.

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
            .authorizeRequests()
                    .antMatchers("/css/**", "/js/**", "/images/**")
                        .permitAll()
                    .anyRequest()
                        .authenticated()
            .and()
                .formLogin()
                    .loginPage("/login") // The custom login page. Here you need to create a login.html file to be used by spring security.
                        .permitAll()
                    // Here spring will search for a file called 'index.html' under resources/static folder because we specified '.html' extension.
                    // if we don't specify the extension, spring will search under resources/templates folder
                    .defaultSuccessUrl("/index.html");  //The page to redirect to after a successful login. This is under formLogin() call.




    }
}
