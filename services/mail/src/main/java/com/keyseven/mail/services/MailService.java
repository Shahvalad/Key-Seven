package com.keyseven.mail.services;

public interface MailService {
    public void sendEmail(String to, String subject, String text);
}
