package com.example.listaurant.common.config;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final SimpleUrlAuthenticationFailureHandler failureHandler;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/css/**","/assets/**","/images/**","/webjars/**","/js/**"
                                ,"/nickname-check", "/email-check","/certification").permitAll()
                        .requestMatchers("/","/sign-up","/login","/board","/temp-password").permitAll()
                                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                                .dispatcherTypeMatchers(DispatcherType.INCLUDE).permitAll()
                        .anyRequest().authenticated()
                        )
                .formLogin(formlogin -> formlogin
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .usernameParameter("email")
                        .passwordParameter("passwd")
                        .failureHandler(failureHandler)
                )
                .authenticationProvider(authenticationProvider)
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true))
                .sessionManagement(session -> session
                        .maximumSessions(1)
                        .expiredUrl("/login"));
        return http.build();
    }

}
