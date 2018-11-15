package com.business.ical.service.interfaces;

import net.fortuna.ical4j.model.Calendar;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

public interface SimpleEvent {
    Calendar simpleEventInvitation() throws ParseException, URISyntaxException, IOException;

    Calendar simpleEventUpdate() throws ParseException, URISyntaxException, IOException;

    Calendar simpleEventReschedule() throws ParseException, URISyntaxException, IOException;

    Calendar simpleEventCancel() throws ParseException, URISyntaxException, IOException;
}
