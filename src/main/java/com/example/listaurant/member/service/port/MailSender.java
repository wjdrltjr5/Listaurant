package com.example.listaurant.member.service.port;

public interface MailSender {
    void send(String to, String tempPassword);
}
