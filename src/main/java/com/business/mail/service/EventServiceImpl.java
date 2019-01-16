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
import javax.mail.Multipart;
import javax.mail.internet.ContentDisposition;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

@Service
public class EventServiceImpl implements EventService {
    private static Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
    private JavaMailSender javaMailSender;
    private MongoEmailResponseRepository mongoEmailResponseRepository;
    private final String HTML_STRING = "<html><style type=\"text/css\"> body, html, .body { background: #f3f3f3 !important; }</style><!-- move the above styles into your custom stylesheet --><spacer size=\"16\"></spacer><container> <row> <columns large=\"4\"> <center> <img src=\"http://placehold.it/125x200\"> </center> </columns> <columns large=\"8\"> <h1>Do Something Radical With This App.</h1> <button class=\"large\" href=\"#\">Sign Up</button> </columns> </row> <spacer size=\"16\"></spacer> <row> <columns> <h3 class=\"text-center\">It's Never Been Easier to Do Things.</h3> <p class=\"text-center\">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Consequatur pariatur unde magni repudiandae totam, accusamus facere eligendi. Ad nobis eius porro saepe et ab, aliquid, sed mollitia cumque suscipit aperiam.</p> </columns> </row> <row> <columns large=\"4\"> <center> <img src=\"http://placehold.it/50x50\"> </center> <h5 class=\"text-center\">Feature One</h5> <p class=\"text-center\">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Rerum, quod quam unde earum.</p> </columns> <columns large=\"4\"> <center> <img src=\"http://placehold.it/50x50\"> </center> <h5 class=\"text-center\">Feature Two</h5> <p class=\"text-center\">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Rerum, quod quam unde earum.</p> </columns> <columns large=\"4\"> <center> <img src=\"http://placehold.it/50x50\"> </center> <h5 class=\"text-center\">Feature Three</h5> <p class=\"text-center\">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Rerum, quod quam unde earum.</p> </columns> </row> <spacer size=\"16\"></spacer> <row> <columns> <h3 class=\"text-center\">What Are You Waiting For? Get Started Today.</h3> <spacer size=\"16\"></spacer> <button class=\"large expand\" href=\"#\">Sign Up</button> </columns> </row> <row class=\"collapsed footer\"> <columns> <spacer size=\"16\"></spacer> <p class=\"text-center\">@copywrite nobody<br/> <a href=\"#\">hello@nocopywrite.com</a> | <a href=\"#\">Manage Email Notifications</a> | <a href=\"#\">Unsubscribe</a></p> <center> <menu> <item><img src=\"http://placehold.it/25\" alt=\"\"></item> <item><img src=\"http://placehold.it/25\" alt=\"\"></item> <item><img src=\"http://placehold.it/25\" alt=\"\"></item> <item><img src=\"http://placehold.it/25\" alt=\"\"></item> <item><img src=\"http://placehold.it/25\" alt=\"\"></item> </menu> </center> </columns> </row> <spacer size=\"16\"></spacer></container></html>";
    private final String URL_TO_IMAGE = "https://tlr.stripocdn.email/content/guids/CABINET_a131f5edf253d3f2d81be57c6397f449/images/52591536587446001.jpg";

    @Autowired
    public EventServiceImpl(JavaMailSender javaMailSender, MongoEmailResponseRepository mongoEmailResponseRepository) {
        this.javaMailSender = javaMailSender;
        this.mongoEmailResponseRepository = mongoEmailResponseRepository;
    }

    @Override
    public EmailResponse sendHtml(String[] to, String subject, Calendar calendar) {
//        MimeMessage message = null;
//
//        try {
//            message = javaMailSender.createMimeMessage();
//
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setTo(to);
//            helper.setSubject(subject);
//
//            MimeMultipart multipart = new MimeMultipart("mixed");
//
//            MimeBodyPart icalInline = new MimeBodyPart();
//            icalInline.setHeader("Content-class", "urn:content-classes:calendarmessage");
//            icalInline.setHeader("Content-ID", "<calendar_message>");
//            icalInline.setHeader("Content-Disposition", "inline");
//            icalInline.setContent(calendar.toString(), "text/calendar;charset=utf-8;method=REQUEST");
//            icalInline.setFileName("icalInline.ics");
//            multipart.addBodyPart(icalInline);
//
//            MimeBodyPart icalAttachment = new MimeBodyPart();
//            icalAttachment.setHeader("Content-class", "urn:content-classes:calendarmessage");
//            icalAttachment.setHeader("Content-Disposition", "attachment");
//            icalAttachment.setContent(calendar.toString(), "text/calendar;charset=utf-8;method=REQUEST");
//            icalAttachment.setFileName("icsAttachment.ics");
//            multipart.addBodyPart(icalAttachment);
//
//
//            message.setContent(multipart);
//
//            javaMailSender.send(message);
//        } catch (MessagingException e) {
//            logger.error("Error with sending message with request method " + e);
//        }
//
//        EmailResponse emailResponse = new EmailResponse(to, subject, message.toString(), null,
//                true);
//
//        mongoEmailResponseRepository.save(emailResponse);

        return null;
    }

    @Override
    public EmailResponse sendEventRequestWithHtml(String[] to, String subject, Calendar calendar) {
        final String htmlBody = "<!DOCTYPE html><html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"><head> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> <meta name=\"viewport\" content=\"initial-scale=1.0\"> <meta name=\"format-detection\" content=\"telephone=no\"> <title>MOSAICO Responsive Email Designer</title> <style type=\"text/css\"> body{ margin: 0; padding: 0; } img{ border: 0px; display: block; } .socialLinks{ font-size: 6px; } .socialLinks a{ display: inline-block; } .long-text p{ margin: 1em 0px; } .long-text p:last-child{ margin-bottom: 0px; } .long-text p:first-child{ margin-top: 0px; } </style> <style type=\"text/css\"> /* yahoo, hotmail */ .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div{ line-height: 100%; } .yshortcuts a{ border-bottom: none !important; } .vb-outer{ min-width: 0 !important; } .RMsgBdy, .ExternalClass{ width: 100%; background-color: #3f3f3f; background-color: #3f3f3f} /* outlook/office365 add buttons outside not-linked images and safari have 2px margin */ [o365] button{ margin: 0 !important; } /* outlook */ table{ mso-table-rspace: 0pt; mso-table-lspace: 0pt; } #outlook a{ padding: 0; } img{ outline: none; text-decoration: none; border: none; -ms-interpolation-mode: bicubic; } a img{ border: none; } @media screen and (max-width: 600px) { table.vb-container, table.vb-row{ width: 95% !important; } .mobile-hide{ display: none !important; } .mobile-textcenter{ text-align: center !important; } .mobile-full{ width: 100% !important; max-width: none !important; } } /* previously used also screen and (max-device-width: 600px) but Yahoo Mail doesn't support multiple queries */ </style> <style type=\"text/css\"> #ko_tripleArticleBlock_4 .links-color a, #ko_tripleArticleBlock_4 .links-color a:link, #ko_tripleArticleBlock_4 .links-color a:visited, #ko_tripleArticleBlock_4 .links-color a:hover{ color: #3f3f3f; color: #3f3f3f; text-decoration: underline } #ko_doubleArticleBlock_6 .links-color a, #ko_doubleArticleBlock_6 .links-color a:link, #ko_doubleArticleBlock_6 .links-color a:visited, #ko_doubleArticleBlock_6 .links-color a:hover{ color: #3f3f3f; color: #3f3f3f; text-decoration: underline } #ko_footerBlock_2 .links-color a, #ko_footerBlock_2 .links-color a:link, #ko_footerBlock_2 .links-color a:visited, #ko_footerBlock_2 .links-color a:hover{ color: #cccccc; color: #cccccc; text-decoration: underline } </style> </head><body bgcolor=\"#3f3f3f\" text=\"#919191\" alink=\"#cccccc\" vlink=\"#cccccc\" style=\"margin: 0; padding: 0; background-color: #3f3f3f; color: #919191;\"><center> <table role=\"presentation\" class=\"vb-outer\" width=\"100%\" cellpadding=\"0\" border=\"0\" cellspacing=\"0\" bgcolor=\"#bfbfbf\" style=\"background-color: #bfbfbf;\" id=\"ko_tripleArticleBlock_4\"> <tbody><tr><td class=\"vb-outer\" align=\"center\" valign=\"top\" style=\"padding-left: 9px; padding-right: 9px; font-size: 0;\"> <!--[if (gte mso 9)|(lte ie 8)]><table role=\"presentation\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"570\"><tr><td align=\"center\" valign=\"top\"><![endif]--><!-- --><div style=\"margin: 0 auto; max-width: 570px; -mru-width: 0px;\"><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"9\" bgcolor=\"#ffffff\" width=\"570\" class=\"vb-row\" style=\"border-collapse: separate; width: 100%; background-color: #ffffff; mso-cellspacing: 9px; border-spacing: 9px; max-width: 570px; -mru-width: 0px;\"> <tbody><tr> <td align=\"center\" valign=\"top\" style=\"font-size: 0;\"><div style=\"width: 100%; max-width: 552px; -mru-width: 0px;\"><!--[if (gte mso 9)|(lte ie 8)]><table role=\"presentation\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"552\"><tr><![endif]--><!-- --><!-- --><!--[if (gte mso 9)|(lte ie 8)]><td align=\"left\" valign=\"top\" width=\"184\"><![endif]--><!-- --><div class=\"mobile-full\" style=\"display: inline-block; vertical-align: top; width: 100%; max-width: 184px; -mru-width: 0px; min-width: calc(33.333333333333336%); max-width: calc(100%); width: calc(304704px - 55200%);\"><!-- --><table role=\"presentation\" class=\"vb-content\" border=\"0\" cellspacing=\"9\" cellpadding=\"0\" style=\"border-collapse: separate; width: 100%; mso-cellspacing: 9px; border-spacing: 9px; -yandex-p: calc(2px - 3%);\" width=\"184\" align=\"left\"> <tbody><tr><td width=\"100%\" valign=\"top\" align=\"center\" class=\"links-color\" style=\"padding-bottom: 9px;\"><!--[if (lte ie 8)]><div style=\"display: inline-block; width: 166px; -mru-width: 0px;\"><![endif]--><img border=\"0\" hspace=\"0\" align=\"center\" vspace=\"0\" width=\"166\" height=\"90\" style=\"border: 0px; display: block; vertical-align: top; height: auto; margin: 0 auto; color: #3f3f3f; font-size: 13px; font-family: Arial, Helvetica, sans-serif; width: 100%; max-width: 166px; height: auto;\" src=\"https://mosaico.io/srv/f-p3kpuea/img?method=placeholder&amp;params=166%2C90\"><!--[if (lte ie 8)]></div><![endif]--></td></tr> <tr> <td width=\"100%\" valign=\"top\" align=\"left\" style=\"font-weight: normal; color: #3f3f3f; font-size: 18px; font-family: Lucida Sans Unicode, Lucida Grande, sans-serif; text-align: left;\"><span style=\"font-weight: normal;\">Title</span></td> </tr> <tr><td class=\"long-text links-color\" width=\"100%\" valign=\"top\" align=\"left\" style=\"font-weight: normal; color: #3f3f3f; font-size: 13px; font-family: Arial, Helvetica, sans-serif; text-align: left; line-height: normal;\"><p style=\"margin: 1em 0px; margin-bottom: 0px; margin-top: 0px;\">Far far away, behind the word mountains, far from the countries <a href=\"\" style=\"color: #3f3f3f; color: #3f3f3f; text-decoration: underline;\">Vokalia and Consonantia</a>, there live the blind texts.</p></td></tr> <tr> <td valign=\"top\" align=\"left\"><table role=\"presentation\" cellpadding=\"6\" border=\"0\" align=\"left\" cellspacing=\"0\" style=\"border-spacing: 0; mso-padding-alt: 6px 6px 6px 6px; padding-top: 4px;\"><tbody><tr> <td width=\"auto\" valign=\"middle\" align=\"left\" bgcolor=\"#bfbfbf\" style=\"text-align: center; font-weight: normal; padding: 6px; padding-left: 18px; padding-right: 18px; background-color: #bfbfbf; color: #3f3f3f; font-size: 13px; font-family: Arial, Helvetica, sans-serif; border-radius: 4px;\"><a style=\"text-decoration: none; font-weight: normal; color: #3f3f3f; font-size: 13px; font-family: Arial, Helvetica, sans-serif;\" target=\"_new\" href=\"\">BUTTON</a></td> </tr></tbody></table></td> </tr> </tbody></table><!-- --></div><!--[if (gte mso 9)|(lte ie 8)]></td><![endif]--><!-- --><!-- --><!--[if (gte mso 9)|(lte ie 8)]><td align=\"left\" valign=\"top\" width=\"184\"><![endif]--><!-- --><div class=\"mobile-full\" style=\"display: inline-block; vertical-align: top; width: 100%; max-width: 184px; -mru-width: 0px; min-width: calc(33.333333333333336%); max-width: calc(100%); width: calc(304704px - 55200%);\"><!-- --><table role=\"presentation\" class=\"vb-content\" border=\"0\" cellspacing=\"9\" cellpadding=\"0\" style=\"border-collapse: separate; width: 100%; mso-cellspacing: 9px; border-spacing: 9px; -yandex-p: calc(2px - 3%);\" width=\"184\" align=\"left\"> <tbody><tr><td width=\"100%\" valign=\"top\" align=\"center\" class=\"links-color\" style=\"padding-bottom: 9px;\"><!--[if (lte ie 8)]><div style=\"display: inline-block; width: 166px; -mru-width: 0px;\"><![endif]--><img border=\"0\" hspace=\"0\" align=\"center\" vspace=\"0\" width=\"166\" height=\"90\" style=\"border: 0px; display: block; vertical-align: top; height: auto; margin: 0 auto; color: #3f3f3f; font-size: 13px; font-family: Arial, Helvetica, sans-serif; width: 100%; max-width: 166px; height: auto;\" src=\"https://mosaico.io/srv/f-p3kpuea/img?method=placeholder&amp;params=166%2C90\"><!--[if (lte ie 8)]></div><![endif]--></td></tr> <tr> <td width=\"100%\" valign=\"top\" align=\"left\" style=\"font-weight: normal; color: #3f3f3f; font-size: 18px; font-family: Lucida Sans Unicode, Lucida Grande, sans-serif; text-align: left;\"><span style=\"font-weight: normal;\">Title</span></td> </tr> <tr><td class=\"long-text links-color\" width=\"100%\" valign=\"top\" align=\"left\" style=\"font-weight: normal; color: #3f3f3f; font-size: 13px; font-family: Arial, Helvetica, sans-serif; text-align: left; line-height: normal;\"><p style=\"margin: 1em 0px; margin-bottom: 0px; margin-top: 0px;\">Far far away, behind the word mountains, far from the countries <a href=\"\" style=\"color: #3f3f3f; color: #3f3f3f; text-decoration: underline;\">Vokalia and Consonantia</a>, there live the blind texts.</p></td></tr> <tr> <td valign=\"top\" align=\"left\"><table role=\"presentation\" cellpadding=\"6\" border=\"0\" align=\"left\" cellspacing=\"0\" style=\"border-spacing: 0; mso-padding-alt: 6px 6px 6px 6px; padding-top: 4px;\"><tbody><tr> <td width=\"auto\" valign=\"middle\" align=\"left\" bgcolor=\"#bfbfbf\" style=\"text-align: center; font-weight: normal; padding: 6px; padding-left: 18px; padding-right: 18px; background-color: #bfbfbf; color: #3f3f3f; font-size: 13px; font-family: Arial, Helvetica, sans-serif; border-radius: 4px;\"><a style=\"text-decoration: none; font-weight: normal; color: #3f3f3f; font-size: 13px; font-family: Arial, Helvetica, sans-serif;\" target=\"_new\" href=\"\">BUTTON</a></td> </tr></tbody></table></td> </tr> </tbody></table><!-- --></div><!--[if (gte mso 9)|(lte ie 8)]></td><![endif]--><!-- --><!-- --><!--[if (gte mso 9)|(lte ie 8)]><td align=\"left\" valign=\"top\" width=\"184\"><![endif]--><!-- --><div class=\"mobile-full\" style=\"display: inline-block; vertical-align: top; width: 100%; max-width: 184px; -mru-width: 0px; min-width: calc(33.333333333333336%); max-width: calc(100%); width: calc(304704px - 55200%);\"><!-- --><table role=\"presentation\" class=\"vb-content\" border=\"0\" cellspacing=\"9\" cellpadding=\"0\" style=\"border-collapse: separate; width: 100%; mso-cellspacing: 9px; border-spacing: 9px; -yandex-p: calc(2px - 3%);\" width=\"184\" align=\"left\"> <tbody><tr><td width=\"100%\" valign=\"top\" align=\"center\" class=\"links-color\" style=\"padding-bottom: 9px;\"><!--[if (lte ie 8)]><div style=\"display: inline-block; width: 166px; -mru-width: 0px;\"><![endif]--><img border=\"0\" hspace=\"0\" align=\"center\" vspace=\"0\" width=\"166\" height=\"90\" style=\"border: 0px; display: block; vertical-align: top; height: auto; margin: 0 auto; color: #3f3f3f; font-size: 13px; font-family: Arial, Helvetica, sans-serif; width: 100%; max-width: 166px; height: auto;\" src=\"https://mosaico.io/srv/f-p3kpuea/img?method=placeholder&amp;params=166%2C90\"><!--[if (lte ie 8)]></div><![endif]--></td></tr> <tr> <td width=\"100%\" valign=\"top\" align=\"left\" style=\"font-weight: normal; color: #3f3f3f; font-size: 18px; font-family: Lucida Sans Unicode, Lucida Grande, sans-serif; text-align: left;\"><span style=\"font-weight: normal;\">Title</span></td> </tr> <tr><td class=\"long-text links-color\" width=\"100%\" valign=\"top\" align=\"left\" style=\"font-weight: normal; color: #3f3f3f; font-size: 13px; font-family: Arial, Helvetica, sans-serif; text-align: left; line-height: normal;\"><p style=\"margin: 1em 0px; margin-bottom: 0px; margin-top: 0px;\">Far far away, behind the word mountains, far from the countries <a href=\"\" style=\"color: #3f3f3f; color: #3f3f3f; text-decoration: underline;\">Vokalia and Consonantia</a>, there live the blind texts.</p></td></tr> <tr> <td valign=\"top\" align=\"left\"><table role=\"presentation\" cellpadding=\"6\" border=\"0\" align=\"left\" cellspacing=\"0\" style=\"border-spacing: 0; mso-padding-alt: 6px 6px 6px 6px; padding-top: 4px;\"><tbody><tr> <td width=\"auto\" valign=\"middle\" align=\"left\" bgcolor=\"#bfbfbf\" style=\"text-align: center; font-weight: normal; padding: 6px; padding-left: 18px; padding-right: 18px; background-color: #bfbfbf; color: #3f3f3f; font-size: 13px; font-family: Arial, Helvetica, sans-serif; border-radius: 4px;\"><a style=\"text-decoration: none; font-weight: normal; color: #3f3f3f; font-size: 13px; font-family: Arial, Helvetica, sans-serif;\" target=\"_new\" href=\"\">BUTTON</a></td> </tr></tbody></table></td> </tr> </tbody></table><!-- --></div><!--[if (gte mso 9)|(lte ie 8)]></td><![endif]--><!-- --><!-- --><!--[if (gte mso 9)|(lte ie 8)]></tr></table><![endif]--></div></td> </tr> </tbody></table></div><!-- --><!--[if (gte mso 9)|(lte ie 8)]></td></tr></table><![endif]--> </td></tr> </tbody></table><table role=\"presentation\" class=\"vb-outer\" width=\"100%\" cellpadding=\"0\" border=\"0\" cellspacing=\"0\" bgcolor=\"#bfbfbf\" style=\"background-color: #bfbfbf;\" id=\"ko_doubleArticleBlock_6\"> <tbody><tr><td class=\"vb-outer\" align=\"center\" valign=\"top\" style=\"padding-left: 9px; padding-right: 9px; font-size: 0;\"> <!--[if (gte mso 9)|(lte ie 8)]><table role=\"presentation\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"570\"><tr><td align=\"center\" valign=\"top\"><![endif]--><!-- --><div style=\"margin: 0 auto; max-width: 570px; -mru-width: 0px;\"><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"9\" bgcolor=\"#ffffff\" width=\"570\" class=\"vb-row\" style=\"border-collapse: separate; width: 100%; background-color: #ffffff; mso-cellspacing: 9px; border-spacing: 9px; max-width: 570px; -mru-width: 0px;\"> <tbody><tr> <td align=\"center\" valign=\"top\" style=\"font-size: 0;\"><div style=\"width: 100%; max-width: 552px; -mru-width: 0px;\"><!--[if (gte mso 9)|(lte ie 8)]><table role=\"presentation\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"552\"><tr><![endif]--><!-- --><!-- --><!--[if (gte mso 9)|(lte ie 8)]><td align=\"left\" valign=\"top\" width=\"276\"><![endif]--><!-- --><div class=\"mobile-full\" style=\"display: inline-block; vertical-align: top; width: 100%; max-width: 276px; -mru-width: 0px; min-width: calc(50%); max-width: calc(100%); width: calc(304704px - 55200%);\"><!-- --><table role=\"presentation\" class=\"vb-content\" border=\"0\" cellspacing=\"9\" cellpadding=\"0\" style=\"border-collapse: separate; width: 100%; mso-cellspacing: 9px; border-spacing: 9px; -yandex-p: calc(2px - 3%);\" width=\"276\" align=\"left\"> <tbody><tr><td width=\"100%\" valign=\"top\" align=\"center\" class=\"links-color\" style=\"padding-bottom: 9px;\"><!--[if (lte ie 8)]><div style=\"display: inline-block; width: 258px; -mru-width: 0px;\"><![endif]--><img border=\"0\" hspace=\"0\" align=\"center\" vspace=\"0\" width=\"258\" height=\"100\" style=\"border: 0px; display: block; vertical-align: top; height: auto; margin: 0 auto; color: #3f3f3f; font-size: 13px; font-family: Arial, Helvetica, sans-serif; width: 100%; max-width: 258px; height: auto;\" src=\"https://mosaico.io/srv/f-p3kpuea/img?method=placeholder&amp;params=258%2C100\"><!--[if (lte ie 8)]></div><![endif]--></td></tr> <tr> <td width=\"100%\" valign=\"top\" align=\"left\" style=\"font-weight: normal; color: #3f3f3f; font-size: 18px; font-family: Lucida Sans Unicode, Lucida Grande, sans-serif; text-align: left;\"><span style=\"font-weight: normal;\">Title</span></td> </tr> <tr><td class=\"long-text links-color\" width=\"100%\" valign=\"top\" align=\"left\" style=\"font-weight: normal; color: #3f3f3f; font-size: 13px; font-family: Arial, Helvetica, sans-serif; text-align: left; line-height: normal;\"><p style=\"margin: 1em 0px; margin-bottom: 0px; margin-top: 0px;\">Far far away, behind the word mountains, far from the countries <a href=\"\" style=\"color: #3f3f3f; color: #3f3f3f; text-decoration: underline;\">Vokalia and Consonantia</a>, there live the blind texts.</p></td></tr> <tr> <td valign=\"top\" align=\"left\"><table role=\"presentation\" cellpadding=\"6\" border=\"0\" align=\"left\" cellspacing=\"0\" style=\"border-spacing: 0; mso-padding-alt: 6px 6px 6px 6px; padding-top: 4px;\"><tbody><tr> <td width=\"auto\" valign=\"middle\" align=\"left\" bgcolor=\"#bfbfbf\" style=\"text-align: center; font-weight: normal; padding: 6px; padding-left: 18px; padding-right: 18px; background-color: #bfbfbf; color: #3f3f3f; font-size: 13px; font-family: Arial, Helvetica, sans-serif; border-radius: 4px;\"><a style=\"text-decoration: none; font-weight: normal; color: #3f3f3f; font-size: 13px; font-family: Arial, Helvetica, sans-serif;\" target=\"_new\" href=\"\">BUTTON</a></td> </tr></tbody></table></td> </tr> </tbody></table><!-- --></div><!--[if (gte mso 9)|(lte ie 8)]></td><![endif]--><!-- --><!-- --><!--[if (gte mso 9)|(lte ie 8)]><td align=\"left\" valign=\"top\" width=\"276\"><![endif]--><!-- --><div class=\"mobile-full\" style=\"display: inline-block; vertical-align: top; width: 100%; max-width: 276px; -mru-width: 0px; min-width: calc(50%); max-width: calc(100%); width: calc(304704px - 55200%);\"><!-- --><table role=\"presentation\" class=\"vb-content\" border=\"0\" cellspacing=\"9\" cellpadding=\"0\" style=\"border-collapse: separate; width: 100%; mso-cellspacing: 9px; border-spacing: 9px; -yandex-p: calc(2px - 3%);\" width=\"276\" align=\"left\"> <tbody><tr><td width=\"100%\" valign=\"top\" align=\"center\" class=\"links-color\" style=\"padding-bottom: 9px;\"><!--[if (lte ie 8)]><div style=\"display: inline-block; width: 258px; -mru-width: 0px;\"><![endif]--><img border=\"0\" hspace=\"0\" align=\"center\" vspace=\"0\" width=\"258\" height=\"100\" style=\"border: 0px; display: block; vertical-align: top; height: auto; margin: 0 auto; color: #3f3f3f; font-size: 13px; font-family: Arial, Helvetica, sans-serif; width: 100%; max-width: 258px; height: auto;\" src=\"https://mosaico.io/srv/f-p3kpuea/img?method=placeholder&amp;params=258%2C100\"><!--[if (lte ie 8)]></div><![endif]--></td></tr> <tr> <td width=\"100%\" valign=\"top\" align=\"left\" style=\"font-weight: normal; color: #3f3f3f; font-size: 18px; font-family: Lucida Sans Unicode, Lucida Grande, sans-serif; text-align: left;\"><span style=\"font-weight: normal;\">Title</span></td> </tr> <tr><td class=\"long-text links-color\" width=\"100%\" valign=\"top\" align=\"left\" style=\"font-weight: normal; color: #3f3f3f; font-size: 13px; font-family: Arial, Helvetica, sans-serif; text-align: left; line-height: normal;\"><p style=\"margin: 1em 0px; margin-bottom: 0px; margin-top: 0px;\">Far far away, behind the word mountains, far from the countries <a href=\"\" style=\"color: #3f3f3f; color: #3f3f3f; text-decoration: underline;\">Vokalia and Consonantia</a>, there live the blind texts.</p></td></tr> <tr> <td valign=\"top\" align=\"left\"><table role=\"presentation\" cellpadding=\"6\" border=\"0\" align=\"left\" cellspacing=\"0\" style=\"border-spacing: 0; mso-padding-alt: 6px 6px 6px 6px; padding-top: 4px;\"><tbody><tr> <td width=\"auto\" valign=\"middle\" align=\"left\" bgcolor=\"#bfbfbf\" style=\"text-align: center; font-weight: normal; padding: 6px; padding-left: 18px; padding-right: 18px; background-color: #bfbfbf; color: #3f3f3f; font-size: 13px; font-family: Arial, Helvetica, sans-serif; border-radius: 4px;\"><a style=\"text-decoration: none; font-weight: normal; color: #3f3f3f; font-size: 13px; font-family: Arial, Helvetica, sans-serif;\" target=\"_new\" href=\"\">BUTTON</a></td> </tr></tbody></table></td> </tr> </tbody></table><!-- --></div><!--[if (gte mso 9)|(lte ie 8)]></td><![endif]--><!-- --><!-- --><!--[if (gte mso 9)|(lte ie 8)]></tr></table><![endif]--></div></td> </tr> </tbody></table></div><!-- --><!--[if (gte mso 9)|(lte ie 8)]></td></tr></table><![endif]--> </td></tr> </tbody></table> <!-- footerBlock --> <table role=\"presentation\" class=\"vb-outer\" width=\"100%\" cellpadding=\"0\" border=\"0\" cellspacing=\"0\" bgcolor=\"#3f3f3f\" style=\"background-color: #3f3f3f;\" id=\"ko_footerBlock_2\"> <tbody><tr><td class=\"vb-outer\" align=\"center\" valign=\"top\" style=\"padding-left: 9px; padding-right: 9px; font-size: 0;\"> <!--[if (gte mso 9)|(lte ie 8)]><table role=\"presentation\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"570\"><tr><td align=\"center\" valign=\"top\"><![endif]--><!-- --><div style=\"margin: 0 auto; max-width: 570px; -mru-width: 0px;\"><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; width: 100%; mso-cellspacing: 0px; border-spacing: 0px; max-width: 570px; -mru-width: 0px;\" width=\"570\" class=\"vb-row\"> <tbody><tr> <td align=\"center\" valign=\"top\" style=\"font-size: 0; padding: 0 9px;\"><div style=\"vertical-align: top; width: 100%; max-width: 552px; -mru-width: 0px;\"><!-- --><table role=\"presentation\" class=\"vb-content\" border=\"0\" cellspacing=\"9\" cellpadding=\"0\" style=\"border-collapse: separate; width: 100%; mso-cellspacing: 9px; border-spacing: 9px;\" width=\"552\"> <tbody><tr><td class=\"long-text links-color\" width=\"100%\" valign=\"top\" align=\"center\" style=\"font-weight: normal; color: #919191; font-size: 13px; font-family: Arial, Helvetica, sans-serif; text-align: center;\"><p style=\"margin: 1em 0px; margin-bottom: 0px; margin-top: 0px;\">Email sent to <a href=\"mailto:[mail]\" style=\"color: #cccccc; color: #cccccc; text-decoration: underline;\">[mail]</a></p></td></tr> <tr><td width=\"100%\" valign=\"top\" align=\"center\" style=\"font-weight: normal; color: #ffffff; font-size: 13px; font-family: Arial, Helvetica, sans-serif; text-align: center;\"><a href=\"[unsubscribe_link]\" style=\"color: #ffffff; text-decoration: underline;\" target=\"_new\">Unsubscribe</a></td></tr> <tr style=\"text-align: center;\"><td width=\"100%\" valign=\"top\" align=\"center\" class=\"links-color\" style=\"text-align: center;\"><!--[if (lte ie 8)]><div style=\"display: inline-block; width: 170px; -mru-width: 0px;\"><![endif]--><a target=\"_new\" href=\"https://mosaico.io/?footerbadge\" style=\"color: #cccccc; color: #cccccc; text-decoration: underline;\"><img alt=\"MOSAICO Responsive Email Designer\" border=\"0\" hspace=\"0\" align=\"center\" vspace=\"0\" width=\"170\" src=\"https://mosaico.io/img/mosaico-badge.gif\" style=\"border: 0px; display: block; vertical-align: top; height: auto; margin: 0 auto; color: #3f3f3f; font-size: 13px; font-family: Arial, Helvetica, sans-serif; width: 100%; max-width: 170px;\"></a><!--[if (lte ie 8)]></div><![endif]--></td></tr> </tbody></table></div></td> </tr> </tbody></table></div><!-- --><!--[if (gte mso 9)|(lte ie 8)]></td></tr></table><![endif]--> </td></tr> </tbody></table> <!-- /footerBlock --> </center></body></html>";
        MimeMessage message;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            messageHelper.setTo(to);
            messageHelper.setSubject(subject);

            MimeBodyPart html = new MimeBodyPart();
            html.setContentID("<32132132131321321321321>");
            html.setContent(htmlBody, "text/html; charset=UTF-8");

            MimeBodyPart icalInline = new MimeBodyPart();
            icalInline.setHeader("Content-class", "urn:content-classes:calendarmessage");
            icalInline.setHeader("Content-Disposition", "inline;");
            icalInline.setContent(calendar.toString(), "text/calendar;charset=utf-8;method=REQUEST");

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(html);
            multipart.addBodyPart(icalInline);
            message.setContent(multipart);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new EmailResponse();
    }

    @Override
    public EmailResponse sendHtmlToEmail(String[] to, String subject, Calendar calendar) {
        return null;
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

        return null;
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

        return null;
    }
}
