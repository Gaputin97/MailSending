package com.business.mail.service;


import com.business.mail.model.Email;
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
    public Email sendSimpleMessage(String to, String subject, String text) {
        return emailRepository.sendSimpleMessage(to, subject, text);
    }

    @Override
    public Email sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
        return emailRepository.sendMessageWithAttachment(to, subject, text, pathToAttachment);
    }

    @Override
    public Email sendMessageWithHtmlText(String to, String subject) {
        return emailRepository.sendMessageWithHtmlText(to,subject);
    }

    @Override
    public Email sendMailWithHtmlInlineImage(String to, String subject) {
        return emailRepository.sendMailWithHtmlInlineImage(to, subject);
    }
}
