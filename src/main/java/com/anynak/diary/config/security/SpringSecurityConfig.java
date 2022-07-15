package com.anynak.diary.config.security;

import com.anynak.diary.data.UserDetailsServiceImpl;
import com.anynak.diary.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public SpringSecurityConfig(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(userRepository);
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder);
        return daoAuthenticationProvider;
    }
   //@Bean
   //public InMemoryUserDetailsManager configAuth(){
   //    return new InMemoryUserDetailsManager();
   //}
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable().authorizeRequests()

                .antMatchers("/api/register","/api").permitAll()
                .antMatchers("/api/users").hasAnyRole("ADMIN")
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .httpBasic();
        return http.build();
    }

    public WebSecurityCustomizer webSecurityCustomizer() throws Exception{
        return (web -> web.ignoring().antMatchers("/images/**", "/js/**"));
    }
}
