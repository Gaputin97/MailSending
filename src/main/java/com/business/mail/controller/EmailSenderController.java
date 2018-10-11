package com.business.mail.controller;

import com.business.mail.model.Email;
import com.business.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/send/email", produces = "application/json")
public class EmailSenderController {

    private final EmailService emailService;

    @Autowired
    public EmailSenderController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping(value = "/simple")
    public Email sendEmail(){
        return emailService.sendSimpleMessage("gaputin@hotmail.com", "Test sending", "Hello world");
    }

    @RequestMapping(value = "/attach")
    public Email sendEmailWithAttachments() {
        return emailService.sendMessageWithAttachment("gaputin@hotmail.com", "Test sending with attachment",
                "Hello world with attachment", "src/main/resources/img/image1.png");
    }

    @RequestMapping(value = "/html")
    public Email sendEmailWithHtmlBody() {
        return emailService.sendMessageWithHtmlText("gaputin@hotmail.com", "Test sending with HTML text");
    }

    @RequestMapping(value = "/image")
    public Email sendEmailWithInlineImg() {
        return emailService.sendMailWithHtmlInlineImage("gaputin@hotmail.com", "Test sending with " +
                "inline image");
    }
}
