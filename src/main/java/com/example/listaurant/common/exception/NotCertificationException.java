package com.example.listaurant.common.exception;

import org.springframework.security.core.AuthenticationException;

public class NotCertificationException extends AuthenticationException {
    public NotCertificationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NotCertificationException(String msg) {
        super(msg);
    }
}
