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

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.time.format.DateTimeFormatter;

import static java.time.ZoneOffset.UTC;

@Service
public class EventServiceImpl implements EventService {
    private static Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
    private static final DateTimeFormatter DATE_TIME_FORMAT_UTC = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")
            .withZone(UTC);
    private JavaMailSender javaMailSender;
    private MongoEmailResponseRepository mongoEmailResponseRepository;

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

            MimeMultipart multipart = new MimeMultipart();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);

            MimeBodyPart icalAttachment = new MimeBodyPart();
            icalAttachment.setHeader("Content-class", "urn:content-classes:calendarmessage");
            icalAttachment.setHeader("Content-Disposition", "attachment");
            icalAttachment.setContent(calendar.toString(), "text/calendar;charset=utf-8;method=REQUEST");
            icalAttachment.setFileName("icsAttachment.ics");
            multipart.addBodyPart(icalAttachment);

            MimeBodyPart icalInline = new MimeBodyPart();
            icalInline.setHeader("Content-class", "urn:content-classes:calendarmessage");
            icalInline.setHeader("Content-ID", "<calendar_message>");
            icalInline.setHeader("Content-Disposition", "inline");
            icalInline.setContent(calendar.toString(), "text/calendar;charset=utf-8;method=REQUEST");
            icalInline.setFileName("icalInline.ics");
            multipart.addBodyPart(icalInline);

            message.setContent(multipart);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            logger.error("Error with sending message with inline images " + e);
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
            logger.error("Error with sending message with inline images " + e);
        }

        EmailResponse emailResponse = new EmailResponse(to, subject, message.toString(), null,
                true);

        mongoEmailResponseRepository.save(emailResponse);

        return emailResponse;
    }
}
