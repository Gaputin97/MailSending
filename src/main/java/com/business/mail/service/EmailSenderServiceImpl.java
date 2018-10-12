package com.business.mail.service;

import com.business.mail.model.EmailResponse;
import com.business.mail.repository.EmailSenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    private EmailSenderRepository emailSenderRepository;

    @Autowired
    public EmailSenderServiceImpl(EmailSenderRepository emailSenderRepository) {
        this.emailSenderRepository = emailSenderRepository;
    }

    @Override
    public EmailResponse sendSimpleMessage(String to, String subject, String text) {
        return emailSenderRepository.sendSimpleMessage(to, subject, text);
    }

    @Override
    public EmailResponse sendMessageWithAttachment(String to, String subject, String pathToAttachment) {
        return emailSenderRepository.sendMessageWithAttachment(to, subject, pathToAttachment);
    }

    @Override
    public EmailResponse sendMessageWithHtmlText(String to, String subject, String htmlString) {
        return emailSenderRepository.sendMessageWithHtmlText(to, subject, htmlString);
    }

    @Override
    public EmailResponse sendMailWithHtmlInlineImage(String to, String subject, String urlOfAttachment) {
        return emailSenderRepository.sendMailWithHtmlInlineImage(to, subject, urlOfAttachment);
    }
}
