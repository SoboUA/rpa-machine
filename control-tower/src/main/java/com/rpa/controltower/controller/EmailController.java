package com.rpa.controltower.controller;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class EmailController {

    @Autowired
    JavaMailSender javaMailSender;

    public String sendMail() {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo("set-your-recipient-email-here@gmail.com");
            helper.setText("How are you?");
            helper.setSubject("Hi");

//            FileSystemResource fileSystemResource = new FileSystemResource(new File("src\\main\\resources\\output\\file.xlsx"));
            FileSystemResource fileSystemResource = new FileSystemResource(new File("file.xlsx"));
//            ClassPathResource fileSystemResource = new ClassPathResource("src\\main\\resources\\output\\file.xlsx");
            helper.addAttachment("file", fileSystemResource, "application/octet-stream;");

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }



        return "11";
    }

    @GetMapping("/send")
    public String sendEmail() {
        sendMail();
        return "hello";
    }
}
