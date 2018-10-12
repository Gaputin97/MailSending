package com.business.mail.repository;

import com.business.mail.model.EmailResponse;
import com.business.mail.service.dao.EmailResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Repository
public class EmailSenderRepositoryImpl implements EmailSenderRepository {

    private Logger logger = LoggerFactory.getLogger(EmailSenderRepositoryImpl.class);

    private String currentDate;

    private final String dateFormat = "dd MMM yyyy HH:mm:ss";

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.ENGLISH);

    private final JavaMailSender javaMailSender;

    private EmailResponse emailResponse;

    private EmailResponseService emailResponseService;

    @Autowired
    public EmailSenderRepositoryImpl(JavaMailSender javaMailSender, EmailResponseService emailResponseService) {
        this.javaMailSender = javaMailSender;
        this.emailResponseService = emailResponseService;
    }

    @Override
    public EmailResponse sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);

        currentDate = simpleDateFormat.format(new Date());

        javaMailSender.send(simpleMailMessage);

        emailResponse = new EmailResponse(to, subject, text, currentDate, true);

        emailResponseService.save(emailResponse);

        return emailResponse;
    }

    @Override
    public EmailResponse sendMessageWithAttachment(String to, String subject, String pathToAttachment) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Default image", file);

            currentDate = simpleDateFormat.format(new Date());

            javaMailSender.send(message);
        } catch (MessagingException e) {
            logger.error("Error sending message with attachment " + e);
        }

        emailResponse = new EmailResponse(to, subject, pathToAttachment, currentDate, true );

        emailResponseService.save(emailResponse);

        return emailResponse;
    }

    @Override
    public EmailResponse sendMessageWithHtmlText(String to, String subject, String htmlString) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            messageHelper.setTo(to);
            messageHelper.setSubject(subject);

            message.setContent(htmlString, "text/html;  charset=UTF-8");

            currentDate = simpleDateFormat.format(new Date());

            javaMailSender.send(message);
        } catch (MessagingException e) {
            logger.error("Error with sending message with HTML text " + e);
        }

        emailResponse = new EmailResponse(to, subject, htmlString, currentDate, true);

        emailResponseService.save(emailResponse);

        return emailResponse;
    }

    @Override
    public EmailResponse sendMailWithHtmlInlineImage(String to, String subject, String urlOfAttachment) {

        MimeMessage message = null;
        try {
            URL url = new URL(urlOfAttachment);

            message = javaMailSender.createMimeMessage();
            Multipart multipart = new MimeMultipart();

            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);

            // Creating and adding inline image using Content-ID to email html page
            MimeBodyPart inlineImage = new MimeBodyPart();
            inlineImage.setContentID("<image@iba.by>");
            inlineImage.setHeader("Content-Disposition", "inline");
            inlineImage.setDataHandler(new DataHandler(url));

            multipart.addBodyPart(inlineImage);
            message.setContent(multipart);

            currentDate = simpleDateFormat.format(new Date());

            javaMailSender.send(message);
        } catch (MessagingException | MalformedURLException e) {
            logger.error("Error with sending message with inline images " + e);
        }

        emailResponse = new EmailResponse(to, subject, message.toString(), currentDate, true);

        emailResponseService.save(emailResponse);

        return emailResponse;
    }
}
