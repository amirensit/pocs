package fr.ntech.jwtspringsec.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    /*
    This passwordEncoder will automatically be used.
    This tells to spring security: when you will verify the password, you will hash the password entered by the user with bcrypt,
    and then compare it with the password stored in password.
    Also it is possible to tells to authenticationBuilder#userDetailsService()#passwordEncoder(passwordEncoder).
    But I think it is optional as it will be picked up automatically. (a)
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}1234").roles("ADMIN", "USER")
                .and()
                .withUser("user").password("{noop}1234").roles("USER");*/
        // this means load users by username and load roles will be done using userDetailsService.
        // Generally eather we use auth.jdbcAuthentication() or we use auth.userDetailsService().
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); // look at (a) above.

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // this tells to spring security to not work with session authentication.
        // http.formLogin();
        http.authorizeRequests().antMatchers("/login/**", "/register/**").permitAll()
                .antMatchers(HttpMethod.POST, "/tasks/**").hasRole("ADMIN")
                .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers ("/h2-console/**");
    }
}
