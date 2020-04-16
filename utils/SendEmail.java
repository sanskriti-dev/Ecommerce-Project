package com.tothenew.ecommerceapp.utils;

import com.tothenew.ecommerceapp.config.EmailConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class SendEmail {
    @Autowired
    private EmailConfiguration emailConfiguration;
    public void sendEmail(String sendTo , String subject, String body)
    {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(emailConfiguration.getHost());
        javaMailSender.setPort(emailConfiguration.getPort());
        javaMailSender.setUsername(emailConfiguration.getUsername());
        javaMailSender.setPassword(emailConfiguration.getPassword());


        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("sanskriti.saluja@ttn.com");
        mailMessage.setTo(sendTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
    }

}
