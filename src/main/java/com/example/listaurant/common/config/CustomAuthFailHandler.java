package com.example.listaurant.common.config;


import com.example.listaurant.common.exception.NotCertificationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

@Component
public class CustomAuthFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String message = "";
        if(exception instanceof BadCredentialsException){
            message = "아이디 또는 비밀번호가 맞지 않습니다.";
        }else if(exception instanceof NotCertificationException){
            message = "이메일 인증을 해주세요";
        } else if (exception instanceof UsernameNotFoundException) {
            message = "아이디 또는 비밀번호가 맞지 않습니다.";
        }

        message = URLEncoder.encode(message, "UTF-8");
        setDefaultFailureUrl("/login?error-message="+message);
        super.onAuthenticationFailure(request, response, exception);
    }
}
