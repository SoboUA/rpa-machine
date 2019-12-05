package com.rpa.controltower.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RestController
public class EmailController {

    @Autowired
    JavaMailSender javaMailSender;

    public String sendMail() {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo("Roman_Sobolevskyi@epam.com");
            helper.setText("How are you?");
            helper.setSubject("Hi");
            System.out.println("we are here");
            FileSystemResource fileSystemResource = new FileSystemResource(new File("src\\main\\resources\\output\\file.xlsx"));
//            FileSystemResource fileSystemResource = new FileSystemResource(new File("file.xlsx"));
//            ClassPathResource fileSystemResource = new ClassPathResource("src\\main\\resources\\output\\file.xlsx");
            helper.addAttachment("file.xlsx", fileSystemResource, "application/octet-stream;");
//            helper.addAttachment("file", fileSystemResource);
            System.out.println("we are here222");

            javaMailSender.send(message);
            System.out.println("mesage sent22");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("exceptio");
        }


        return "11";
    }

    @GetMapping("/send")
    public String sendEmail() {
        sendMail();
        return "hello";
    }

    public String sendMail(String to) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setText("See attached");
            helper.setSubject("Result data");
            FileSystemResource fileSystemResource = new FileSystemResource(new File("src\\main\\resources\\output\\file.xlsx"));
            helper.addAttachment("file.xlsx", fileSystemResource, "application/octet-stream;");

            javaMailSender.send(message);
            System.out.println("mesage sent22");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return "done";
    }


}
