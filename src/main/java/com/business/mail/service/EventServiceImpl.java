package com.business.mail.service;

import com.business.mail.model.EmailResponse;
import com.business.mail.repository.MongoEmailResponseRepository;
import net.fortuna.ical4j.model.Calendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class EventServiceImpl implements EventService {
    private static Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
    private JavaMailSender javaMailSender;
    private MongoEmailResponseRepository mongoEmailResponseRepository;
    private final String HTML_STRING = "<!DOCTYPE HTML PUBLIC><html><head><title>Simple One Column Layout</title><meta charset=\"utf-8\" /><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"/><style>html, body, div, p, ul, ol, li, h1, h2, h3, h4, h5, h6 {margin: 0;padding: 0;}body {font-size:10px;line-height:10px;}@media all and (max-width: 599px) {.container600 {width: 100%;}}</style></head><body style=\"background-color:#F4F4F4;\"><div style=\"background-color:#F4F4F4;\"><div style=\"width:600px;margin:0 auto;\"><div style=\"padding: 10px;\"><div style=\"padding:30px; background-color:#FFFFFF;color:#58585A;\"><img alt=\"\" src=\"https://edmdesigner.github.io/modern-html-email-tutorial/lesson03/step-00/img/logo.png\" style=\"display:block;width:210px\"/></div><div style=\"padding:30px; background-color:#F8F7F0;color:#58585A;\"><h1 style=\"font-size:28px;line-height:32px;margin-bottom:24px;\">Confirm Registration</h1><p style=\"font-size:16px;line-height:20px;font-family: Georgia, Arial, sans-serif;\">But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness.</p><a href=\"http://edmdesigner.com\" style=\"display:block;color:#58585A;font-size:16px;line-height:20px;font-family:Georgia,Arial,sans-serif; text-align:center;padding-top:30px;padding-bottom:30px;\">Confirm</a></div><div style=\"padding:30px; background-color:#58585A;color:#FFFFFF;\"><p style=\"font-size:16px;line-height:20px;font-family: Georgia, Arial, sans-serif; text-align: center;\">2017 @ COPYRIGHT - EDMDESIGNER</p></div></div></div></div></body></html>";
    private final String URL_TO_IMAGE = "https://tlr.stripocdn.email/content/guids/CABINET_a131f5edf253d3f2d81be57c6397f449/images/52591536587446001.jpg";

    @Autowired
    public EventServiceImpl(JavaMailSender javaMailSender, MongoEmailResponseRepository mongoEmailResponseRepository) {
        this.javaMailSender = javaMailSender;
        this.mongoEmailResponseRepository = mongoEmailResponseRepository;
    }

    @Override
    public EmailResponse sendEventRequest(String[] to, String subject, Calendar calendar) {
        MimeMessage message = null;

        try {
            message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);

            MimeMultipart multipart = new MimeMultipart("mixed");

            MimeBodyPart icalInline = new MimeBodyPart();
            icalInline.setHeader("Content-class", "urn:content-classes:calendarmessage");
            icalInline.setHeader("Content-ID", "<calendar_message>");
            icalInline.setHeader("Content-Disposition", "inline");
            icalInline.setContent(calendar.toString(), "text/calendar;charset=utf-8;method=REQUEST");
            icalInline.setFileName("icalInline.ics");
            multipart.addBodyPart(icalInline);

            MimeBodyPart icalAttachment = new MimeBodyPart();
            icalAttachment.setHeader("Content-class", "urn:content-classes:calendarmessage");
            icalAttachment.setHeader("Content-Disposition", "attachment");
            icalAttachment.setContent(calendar.toString(), "text/calendar;charset=utf-8;method=REQUEST");
            icalAttachment.setFileName("icsAttachment.ics");
            multipart.addBodyPart(icalAttachment);


            message.setContent(multipart);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            logger.error("Error with sending message with request method " + e);
        }

        EmailResponse emailResponse = new EmailResponse(to, subject, message.toString(), null,
                true);

        mongoEmailResponseRepository.save(emailResponse);

        return emailResponse;
    }

    @Override
    public EmailResponse sendEventRequestWithHtml(String[] to, String subject, Calendar calendar) {
        MimeMessage message = null;

        try {
            message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);

            MimeMultipart multipart = new MimeMultipart("mixed");

            MimeBodyPart icalAttachment = new MimeBodyPart();
            icalAttachment.setHeader("Content-class", "urn:content-classes:calendarmessage");
            icalAttachment.setHeader("Content-Disposition", "attachment");
            icalAttachment.setContent(calendar.toString(), "text/calendar;charset=utf-8;method=REQUEST");
            icalAttachment.setFileName("icsAttachment.ics");
            multipart.addBodyPart(icalAttachment);

            MimeBodyPart icalInline = new MimeBodyPart();
            icalInline.setHeader("Content-class", "urn:content-classes:calendarmessage");
            icalInline.setHeader("Content-ID", "calendar_message");
            icalInline.setHeader("Content-Disposition", "inline");
            icalInline.setContent(calendar.toString(), "text/calendar;charset=utf-8;method=REQUEST");
            icalInline.setFileName("icalInline.ics");
            multipart.addBodyPart(icalInline);

            MimeBodyPart htmlInline = new MimeBodyPart();
            htmlInline.setHeader("Content-ID", "<html-with-cid>");
            htmlInline.setHeader("Content-Disposition", "inline");
            htmlInline.setHeader("Content-Transfer-Encoding", "quoted-printable");
            htmlInline.setContent(HTML_STRING, "text/html; charset=UTF-8");
            multipart.addBodyPart(htmlInline);

            MimeBodyPart inlineImage = new MimeBodyPart();
            inlineImage.setHeader("Content-ID", "<inline-image>");
            inlineImage.setHeader("Content-Disposition", "inline");
            try {
                inlineImage.setDataHandler(new DataHandler(new URL(URL_TO_IMAGE)));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
//            multipart.addBodyPart(inlineImage);

            message.setContent(multipart);
            message.saveChanges();

            javaMailSender.send(message);
        } catch (MessagingException e) {
            logger.error("Error with sending message with html  " + e);
        }

        EmailResponse emailResponse = new EmailResponse(to, subject, message.toString(), null,
                true);

        mongoEmailResponseRepository.save(emailResponse);

        return emailResponse;
    }

    @Override
    public EmailResponse sendEventRequest(String[] to, String subject, String calendar) {
        MimeMessage message = null;

        try {
            message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);

            MimeMultipart multipart = new MimeMultipart();

            MimeBodyPart icalAttachment = new MimeBodyPart();
            icalAttachment.setHeader("Content-class", "urn:content-classes:calendarmessage");
            icalAttachment.setHeader("Content-Disposition", "attachment");
            icalAttachment.setContent(calendar, "text/calendar;charset=utf-8;method=REQUEST");
            icalAttachment.setFileName("icsAttachment.ics");
            multipart.addBodyPart(icalAttachment);

            MimeBodyPart icalInline = new MimeBodyPart();
            icalInline.setHeader("Content-class", "urn:content-classes:calendarmessage");
            icalInline.setHeader("Content-ID", "calendar_message");
            icalInline.setHeader("Content-Disposition", "inline");
            icalAttachment.setHeader("Content-Transfer-Encoding", "base64");
            icalInline.setContent(calendar, "text/calendar;charset=utf-8;method=REQUEST");
            icalInline.setFileName("icalInline.ics");
            multipart.addBodyPart(icalInline);

            message.setContent(multipart);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            logger.error("Error with sending message with html  " + e);
        }

        EmailResponse emailResponse = new EmailResponse(to, subject, message.toString(), null,
                true);

        mongoEmailResponseRepository.save(emailResponse);

        return emailResponse;
    }

    @Override
    public EmailResponse sendEventPublish(String[] to, String subject, Calendar calendar) {
        MimeMessage message = null;

        try {
            message = javaMailSender.createMimeMessage();

            MimeMultipart multipart = new MimeMultipart();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);

            MimeBodyPart icalAttachment = new MimeBodyPart();
            icalAttachment.setHeader("Content-class", "urn:content-classes:calendarmessage");
            icalAttachment.setHeader("Content-Disposition", "attachment");
            icalAttachment.setContent(calendar.toString(), "text/calendar;charset=utf-8;method=PUBLISH");
            icalAttachment.setFileName("icsAttachment.ics");
            multipart.addBodyPart(icalAttachment);

            MimeBodyPart icalInline = new MimeBodyPart();
            icalInline.setHeader("Content-class", "urn:content-classes:calendarmessage");
            icalInline.setHeader("Content-ID", "<calendar_message>");
            icalInline.setHeader("Content-Disposition", "inline");
            icalInline.setContent(calendar.toString(), "text/calendar;charset=utf-8;method=PUBLISH");
            icalInline.setFileName("icalInline.ics");
            multipart.addBodyPart(icalInline);

            message.setContent(multipart);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            logger.error("Error with sending message with publish method " + e);
        }

        EmailResponse emailResponse = new EmailResponse(to, subject, message.toString(), null,
                true);

        mongoEmailResponseRepository.save(emailResponse);

        return emailResponse;
    }

    @Override
    public EmailResponse sendEventCancel(String[] to, String subject, Calendar calendar) {
        MimeMessage message = null;

        try {
            message = javaMailSender.createMimeMessage();

            MimeMultipart multipart = new MimeMultipart();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);

            MimeBodyPart icalAttachment = new MimeBodyPart();
            icalAttachment.setHeader("Content-class", "urn:content-classes:calendarmessage");
            icalAttachment.setHeader("Content-Disposition", "attachment");
            icalAttachment.setContent(calendar.toString(), "text/calendar;charset=utf-8;method=CANCEL");
            icalAttachment.setFileName("icsAttachment.ics");
            multipart.addBodyPart(icalAttachment);

            MimeBodyPart icalInline = new MimeBodyPart();
            icalInline.setHeader("Content-class", "urn:content-classes:calendarmessage");
            icalInline.setHeader("Content-ID", "<calendar_message>");
            icalInline.setHeader("Content-Disposition", "inline");
            icalInline.setContent(calendar.toString(), "text/calendar;charset=utf-8;method=CANCEL");
            icalInline.setFileName("icalInline.ics");
            multipart.addBodyPart(icalInline);

            message.setContent(multipart);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            logger.error("Error with sending message with cancel method " + e);
        }

        EmailResponse emailResponse = new EmailResponse(to, subject, message.toString(), null,
                true);

        mongoEmailResponseRepository.save(emailResponse);

        return emailResponse;
    }
}
