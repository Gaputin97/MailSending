package com.business.mail.repository;

import com.business.mail.model.Email;
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

@Repository
public class EmailRepositoryImpl implements EmailRepository {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailRepositoryImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public Email sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);

        return new Email(to, subject, text, true);
    }

    @Override
    public Email sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
        MimeMessage message = null;
        MimeMessageHelper helper;
        try {
            message = javaMailSender.createMimeMessage();
            helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Default image", file);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return new Email(to, subject, message, false);
        }
        return new Email(to, subject, message, true);
    }

    @Override
    public Email sendMessageWithHtmlText(String to, String subject) {
        final String htmlBody = "<h1>Hey man</h1>\n" + "<h2>Hey man</h2>";
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            messageHelper.setTo(to);
            messageHelper.setSubject(subject);

            message.setContent(htmlBody, "text/html;  charset=UTF-8");

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return new Email(to, subject, message, false);
        }
        return new Email(to, subject, message, true);
    }

    @Override
    public Email sendMailWithHtmlInlineImage(String to, String subject) {
        final String HTML_BODY = "<h1>Hey man</h1>\n" + "<h2>Hey man</h2>\n" + "<img src=\"cid:image@iba.by\"/>";
        final String BANNER_URL = "https://i1.wp.com/technology.amis.nl/wp-content/uploads/2018/01/spring-boot.png?" +
                "fit=702%2C336&ssl=1";
        MimeMessage message = null;

        try {
            URL url = new URL(BANNER_URL);

            message = javaMailSender.createMimeMessage();
            Multipart multipart = new MimeMultipart();

            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);

            // Creating and adding html part to email html page
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(HTML_BODY, "text/html; charset=UTF-8");

            // Creating and adding inline image using Content-ID to email html page
            MimeBodyPart inlineImage = new MimeBodyPart();
            inlineImage.setContentID("<image@iba.by>");
            inlineImage.setHeader("Content-Disposition", "inline");
            inlineImage.setDataHandler(new DataHandler(url));

            multipart.addBodyPart(htmlPart);
            multipart.addBodyPart(inlineImage);
            message.setContent(multipart);

            javaMailSender.send(message);
        } catch (MessagingException | MalformedURLException e) {
            e.printStackTrace();
            return new Email(to, subject, message, false);
        }
        return new Email(to, subject, message, true);
    }
}
