package com.business.mail.service;

import com.business.mail.model.EmailResponse;

public interface EmailService {

    EmailResponse sendSimpleMessage(String to, String subject, String text);

    EmailResponse sendMessageWithAttachment(String to, String subject, String pathToAttachment);

    EmailResponse sendMessageWithHtmlText(String to, String subject, String htmlString);

    EmailResponse sendMailWithHtmlInlineImage(String to, String subject, String urlOfAttachment);
}
