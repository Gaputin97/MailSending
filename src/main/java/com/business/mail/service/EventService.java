package com.business.mail.service;

import com.business.mail.model.EmailResponse;
import net.fortuna.ical4j.model.Calendar;

public interface EventService {
    EmailResponse sendEventRequest(String[] to, String subject, Calendar calendar);
    EmailResponse sendEventRequest(String[] to, String subject, String calendar);
    EmailResponse sendEventRequestWithHtml(String[] to, String subject, Calendar calendar);
    EmailResponse sendEventPublish(String[] to, String subject, Calendar calendar);
    EmailResponse sendEventCancel(String[] to, String subject, Calendar calendar);
}
