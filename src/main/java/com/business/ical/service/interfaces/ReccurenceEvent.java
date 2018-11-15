package com.business.ical.service.interfaces;

import net.fortuna.ical4j.model.Calendar;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

public interface ReccurenceEvent {
    Calendar recurrenceEventInvitation() throws ParseException, URISyntaxException, IOException;

    Calendar recurrenceEventUpdate() throws ParseException, URISyntaxException, IOException;

    Calendar recurrenceEventReschedule() throws ParseException, URISyntaxException, IOException;

    Calendar recurrenceEventCancel() throws ParseException, URISyntaxException, IOException;
}
