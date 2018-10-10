package com.mail.controller;

import com.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class EmailSenderController {

    private final EmailService emailService;

    @Autowired
    public EmailSenderController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping(value = "/sendemail")
    public String sendEmail(){

        emailService.sendSimpleMessage("gaputin@hotmail.com", "Test sending", "Hello world");
        return "Email sent successfully";
    }

    @RequestMapping(value = "/sendemailatt")
    public String sendEmailWithAttachments(){

        try {
            emailService.sendMessageWithAttachment("gaputin@hotmail.com", "Test sending with attachment", "Hello world with attachment", "src/main/resources/img/image1.png");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "Email with attachments successfully sent";
    }
}
