package com.rpa.controltower.controller;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class EmailController {

    @Autowired
    JavaMailSender javaMailSender;

    public String sendMail() {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo("set-your-recipient-email-here@gmail.com");
            helper.setText("How are you?");
            helper.setSubject("Hi");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(message);

        return "11";
    }

    @GetMapping("/send")
    public String sendEmail() {
        sendMail();
        return "hello";
    }
}
