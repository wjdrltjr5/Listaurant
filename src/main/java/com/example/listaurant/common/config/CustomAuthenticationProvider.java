package com.example.listaurant.common.config;

import com.example.listaurant.common.exception.NotCertificationException;
import com.example.listaurant.member.domain.MemberStatus;
import com.example.listaurant.member.service.MemberDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = (String)authentication.getCredentials();
        MemberDetails member = (MemberDetails)userDetailsService.loadUserByUsername(email);

        if(member.getStatus().equals(MemberStatus.PENDING.toString())){
            throw new NotCertificationException("Member Status is PENDING");
        }
        if(!passwordEncoder.matches(password, member.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(member,null,member.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
