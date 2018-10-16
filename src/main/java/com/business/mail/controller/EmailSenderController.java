package com.business.mail.controller;

import com.business.mail.model.EmailRequest;
import com.business.mail.model.EmailResponse;
import com.business.mail.service.EmailSenderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/send/email", produces = "application/json", consumes = "application/json")
public class EmailSenderController {

    private final EmailSenderService emailSenderService;

    @Autowired
    public EmailSenderController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @ApiOperation(value = "Send simple mail")
    @PostMapping(value = "/simple")
    public EmailResponse sendEmail(@RequestBody EmailRequest emailRequest){
        return emailSenderService.sendSimpleMessage(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), emailRequest.getMessageBody());
    }

    @ApiOperation(value = "Send mail with attachment")
    @PostMapping(value = "/attachment")
    public EmailResponse sendEmailWithAttachments(@RequestBody EmailRequest emailRequest) {
        return emailSenderService.sendMessageWithAttachment(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), emailRequest.getMessageBody());
    }

    @ApiOperation(value = "Send mail with HTML body")
    @PostMapping(value = "/html")
    public EmailResponse sendEmailWithHtmlBody(@RequestBody EmailRequest emailRequest) {
        return emailSenderService.sendMessageWithHtmlText(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), emailRequest.getMessageBody());
    }

    @ApiOperation(value = "Send mail with inline image")
    @PostMapping(value = "/image")
    public EmailResponse sendEmailWithInlineImg(@RequestBody EmailRequest emailRequest) {
        return emailSenderService.sendMailWithHtmlInlineImage(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), emailRequest.getMessageBody());
    }
}
