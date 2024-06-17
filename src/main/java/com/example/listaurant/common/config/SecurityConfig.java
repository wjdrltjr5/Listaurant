package com.example.listaurant.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers("/css/**","/assets/**","/images/**");
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .authorizeHttpRequests(request -> request
                        .requestMatchers("/css/**","assets/**","images/**","webjars/**").permitAll()
                        .requestMatchers("/","/sign-up","/login","/main").permitAll()
                                .anyRequest().permitAll()
                        )
                .csrf(config -> config.disable())
                .formLogin(formlogin -> formlogin
                        .loginPage("/login")
                        .loginProcessingUrl("/loginPro")
                        .defaultSuccessUrl("/")
                        .usernameParameter("email")
                        .passwordParameter("passwd"))
                .logout(logout -> logout.logoutUrl("/logout"));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
