package com.business.ical.service.interfaces;

import net.fortuna.ical4j.model.Calendar;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

public interface ComplexEvents {
    Calendar complexEventInvitation() throws ParseException, URISyntaxException, IOException;
    Calendar complexEventUpdate() throws ParseException, URISyntaxException, IOException;
    Calendar complexEventReschedule() throws ParseException, URISyntaxException, IOException;
    Calendar complexEventCancel() throws ParseException, URISyntaxException, IOException;
}
