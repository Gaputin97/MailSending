package com.business.mail.service;

import com.business.mail.model.EmailResponse;
import net.fortuna.ical4j.model.Calendar;

public interface EventService {
    EmailResponse sendHtml(String[] to, String subject, Calendar calendar);
    EmailResponse sendHtmlToEmail(String[] to, String subject, Calendar calendar);
    EmailResponse sendEventRequestWithHtml(String[] to, String subject, Calendar calendar);
    EmailResponse sendEventPublish(String[] to, String subject, Calendar calendar);
    EmailResponse sendEventCancel(String[] to, String subject, Calendar calendar);
}
