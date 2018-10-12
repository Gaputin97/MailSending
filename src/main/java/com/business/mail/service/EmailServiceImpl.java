package com.business.mail.service;

import com.business.mail.model.EmailResponse;
import com.business.mail.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {

    private EmailRepository emailRepository;

    @Autowired
    public EmailServiceImpl(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @Override
    public EmailResponse sendSimpleMessage(String to, String subject, String text) {
        return emailRepository.sendSimpleMessage(to, subject, text);
    }

    @Override
    public EmailResponse sendMessageWithAttachment(String to, String subject, String pathToAttachment) {
        return emailRepository.sendMessageWithAttachment(to, subject, pathToAttachment);
    }

    @Override
    public EmailResponse sendMessageWithHtmlText(String to, String subject, String htmlString) {
        return emailRepository.sendMessageWithHtmlText(to, subject, htmlString);
    }

    @Override
    public EmailResponse sendMailWithHtmlInlineImage(String to, String subject, String urlOfAttachment) {
        return emailRepository.sendMailWithHtmlInlineImage(to, subject, urlOfAttachment);
    }
}
