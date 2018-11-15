package com.business.ical.service.implementation;

import com.business.ical.service.interfaces.ComplexEvents;
import net.fortuna.ical4j.model.Calendar;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

public class CompleEventsImpl implements ComplexEvents {
    @Override
    public Calendar complexEventInvitation() throws ParseException, URISyntaxException, IOException {
        return null;
    }

    @Override
    public Calendar complexEventUpdate() throws ParseException, URISyntaxException, IOException {
        return null;
    }

    @Override
    public Calendar complexEventReschedule() throws ParseException, URISyntaxException, IOException {
        return null;
    }

    @Override
    public Calendar complexEventCancel() throws ParseException, URISyntaxException, IOException {
        return null;
    }
}
