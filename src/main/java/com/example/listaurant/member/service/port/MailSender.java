package com.example.listaurant.member.service.port;

public interface MailSender {
    void sendTempPassword(String to, String tempPassword);

    void sendCertificationCode(String to, String code);
}
