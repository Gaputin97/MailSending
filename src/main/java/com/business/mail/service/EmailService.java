package com.business.mail.service;

import com.business.mail.model.Email;

public interface EmailService {

    Email sendSimpleMessage(String to, String subject, String text);

    Email sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);

    Email sendMessageWithHtmlText(String to, String subject);

    Email sendMailWithHtmlInlineImage(String to, String subject);
}
