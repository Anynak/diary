package com.anynak.diary.config.security;

import com.anynak.diary.config.security.data.UserDetailsServiceImpl;
import com.anynak.diary.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//https://www.baeldung.com/spring-deprecated-websecurityconfigureradapter
public class SpringSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    //@Autowired
    //@Qualifier("customAuthenticationEntryPoint")
    //AuthenticationEntryPoint authEntryPoint;

    //@Autowired
    //AccessDeniedHandler accessDeniedHandler;
    //@Bean
    //public AccessDeniedHandler accessDeniedHandler(){
    //    return new CustomAccessDeniedHandler();
    //}

    //@Bean
    //public AuthenticationEntryPoint AuthenticationEntryPoint(){
    //    return new CustomAuthenticationEntryPoint();
    //}
    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }
    public SpringSecurityConfig(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(userRepository);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder);
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable().authorizeRequests()

                .antMatchers("/api/register", "/logout", "/login", "/authenticationError").permitAll()
                .antMatchers("/api/roles").hasRole("ADMIN")
                .antMatchers("/api/banUser/**").hasAnyRole("ADMIN","MODERATOR")
                .antMatchers("/api/makeDiaryPublic","/api/strangerPost").hasAnyRole("USER")
                .antMatchers("/api/makeDiaryPublic","/api/strangerPost").not().hasAnyRole("BANNED")

                .anyRequest().authenticated()

                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .httpBasic()
                .and()
                .exceptionHandling()
                //.authenticationEntryPoint(AuthenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler());
        return http.build();
    }

}
