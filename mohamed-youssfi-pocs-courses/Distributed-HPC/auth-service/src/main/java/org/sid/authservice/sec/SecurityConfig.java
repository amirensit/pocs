package org.sid.authservice.sec;

import org.sid.authservice.sec.filters.JwtAuthenticationFilter;
import org.sid.authservice.sec.filters.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

//    public SecurityConfig(@Lazy AccountService accountService) { // problem of circular dependency using constructor injections
//        this.accountService = accountService;
//    }

    @Override // we don't need to override this method as used in jhipster. UserDetailsService is implicitly applied
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    /*
    Not sure why '/login' works as expected. Maybe spring security use this endpoint automatically
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users")
                .hasAuthority("ADMIN");*/
        http.authorizeRequests().antMatchers("/refreshToken/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // this means that we are going to use stateless mode.
        // http.formLogin(); does not have sense anymore in case with stateless mode.
        http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
