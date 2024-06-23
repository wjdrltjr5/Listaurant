package com.example.listaurant.member.infra;

import com.example.listaurant.member.service.port.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Component
public class MailSenderImpl implements MailSender {

    private final JavaMailSender mailSender;
        @Override
    public void sendTempPassword(String to, String tempPassword){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Listaurant@good.com");
        message.setTo(to);
        message.setSubject("Listaurant 임시비밀번호 발급");
        message.setText("임시비밀번호는 : " + tempPassword +"\n 로그인 후 비밀번호를 변경해 주세요");
        mailSender.send(message);
    }

    @Override
    public void sendCertificationCode(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Listaurant@good.com");
        message.setTo(to);
        message.setSubject("Listaurant 인증");
        message.setText("이메일 인증 url입니다. : http://localhost:8080/certification?code=" + code+"&email="+to);
        mailSender.send(message);
    }
}
