package com.business.mail.service;

import com.business.mail.model.EmailResponse;
import com.business.mail.service.mongo.MongoEmailResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.ZoneOffset.UTC;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    private static Logger logger = LoggerFactory.getLogger(EmailSenderServiceImpl.class);

    private static final DateTimeFormatter DATE_TIME_FORMAT_UTC = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")
            .withZone(UTC);

    private final JavaMailSender javaMailSender;

    private final MongoEmailResponseService mongoEmailResponseService;

    @Autowired
    public EmailSenderServiceImpl(JavaMailSender javaMailSender, MongoEmailResponseService mongoEmailResponseService) {
        this.javaMailSender = javaMailSender;
        this.mongoEmailResponseService = mongoEmailResponseService;
    }

    @Override
    public EmailResponse sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);

        String sendingDateTime = ZonedDateTime.now().format(DATE_TIME_FORMAT_UTC);

        javaMailSender.send(simpleMailMessage);

        logger.info(sendingDateTime);

        EmailResponse emailResponse = new EmailResponse(to, subject, text, sendingDateTime, true);
        
        mongoEmailResponseService.save(emailResponse);

        return emailResponse;
    }

    @Override
    public EmailResponse sendMessageWithAttachment(String to, String subject, String pathToAttachment) {
        String sendingDateTime = null;

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Default image", file);

            sendingDateTime = LocalDateTime.now().format(DATE_TIME_FORMAT_UTC);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            logger.error("Error sending message with attachment " + e);
        }

        EmailResponse emailResponse = new EmailResponse(to, subject, pathToAttachment, sendingDateTime, true );

        mongoEmailResponseService.save(emailResponse);

        return emailResponse;
    }

    @Override
    public EmailResponse sendMessageWithHtmlText(String to, String subject, String htmlString) {
        String sendingDateTime = null;

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            messageHelper.setTo(to);
            messageHelper.setSubject(subject);

            message.setContent(htmlString, "text/html;  charset=UTF-8");

            sendingDateTime = LocalDateTime.now().format(DATE_TIME_FORMAT_UTC);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            logger.error("Error with sending message with HTML text " + e);
        }

        EmailResponse emailResponse = new EmailResponse(to, subject, htmlString, sendingDateTime, true);

        mongoEmailResponseService.save(emailResponse);

        return emailResponse;
    }

    @Override
    public EmailResponse sendMailWithHtmlInlineImage(String to, String subject, String urlOfAttachment) {

        String sendingDateTime = null;

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

            sendingDateTime = LocalDateTime.now().format(DATE_TIME_FORMAT_UTC);

            javaMailSender.send(message);
        } catch (MessagingException | MalformedURLException e) {
            logger.error("Error with sending message with inline images " + e);
        }

        EmailResponse emailResponse = new EmailResponse(to, subject, message.toString(), sendingDateTime, true);

        mongoEmailResponseService.save(emailResponse);

        return emailResponse;
    }
}
