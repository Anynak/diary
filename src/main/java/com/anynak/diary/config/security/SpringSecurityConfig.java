package com.anynak.diary.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    @Autowired
    public SpringSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    protected UserDetailsService userDetailsService() {
        return null;
    }
    @Bean
    public InMemoryUserDetailsManager configAuth(){
        return new InMemoryUserDetailsManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().antMatchers("/","/index","/login","/register").permitAll()
                .anyRequest().authenticated()
                .antMatchers("/account").hasAnyAuthority("USER")
                .and()
                .formLogin().and()
                .httpBasic();
        return http.build();
    }
    @Bean
    public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().antMatchers("/","/index","/login","/register").permitAll()
                .anyRequest().authenticated()
                .antMatchers("/account").hasAnyAuthority("USER")
                .and()
                .formLogin().and()
                .httpBasic();
        return http.build();
    }
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception{
        return (web -> web.ignoring().antMatchers("/images/**", "/js/**"));
    }
}
