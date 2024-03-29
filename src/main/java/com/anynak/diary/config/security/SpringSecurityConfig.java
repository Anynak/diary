package com.anynak.diary.config.security;

import com.anynak.diary.config.security.data.UserDetailsServiceImpl;
import com.anynak.diary.repositories.UserRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    public AccessDeniedHandler accessDeniedHandler() {
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
    //@Order(2)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //https://docs.spring.io/spring-security/site/docs/3.0.x/reference/el-access.html
        //https://www.marcobehler.com/guides/spring-security
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/register", "/logout", "/login", "/authenticationError").permitAll()
                .antMatchers("/api/roles").hasRole("ADMIN")
                .antMatchers("/api/banUser/**").hasAnyRole("ADMIN", "MODERATOR")
                //.antMatchers("/api/strangePost","/api/makeDiaryPublic").not().hasRole("BANNED")
                .antMatchers("/api/strangePost", "/api/makeDiaryPublic").access("isFullyAuthenticated() and !hasRole('BANNED')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .httpBasic()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler());
        return http.build();
    }

    //@Bean
    //@Order(1)
    //public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
    //        http.authorizeRequests().antMatchers("/api/strangePost","/api/makeDiaryPublic").not().hasAnyRole("BANNED");
    //        return http.build();
    //    }
    //    //.access("!hasRole('ROLE_BANNED')")
}
