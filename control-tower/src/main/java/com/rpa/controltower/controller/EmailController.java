package com.rpa.controltower.controller;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class EmailController {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendEmail(String to, Workbook workbook){
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        try {
//            workbook.write(bos);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                bos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        byte[] excelBytes = bos.toByteArray();
//        ByteArrayDataSource
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        try {
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//            helper.setTo(to);
//            helper.setSubject("Test run");
//            helper.setText("<h3>See attachment below</h3>", true);
//            helper.addAttachment("events_excel", excelBytes);
//        } catch (MessagingException e) {
//            System.out.println("error in message");
//            e.printStackTrace();
//        }
//
    }
}
