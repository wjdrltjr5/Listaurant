package com.example.listaurant.common.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/css/**","/assets/**","/images/**","/webjars/**").permitAll()
                        .requestMatchers("/","/sign-up").permitAll()
                                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                                .dispatcherTypeMatchers(DispatcherType.INCLUDE).permitAll()
                        .anyRequest().authenticated()
                        )
                .formLogin(formlogin -> formlogin
                        .loginPage("/login").permitAll()
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .usernameParameter("email")
                        .passwordParameter("passwd"))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true))
                .sessionManagement(session -> session
                        .maximumSessions(1)
                        .expiredUrl("/login"));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
