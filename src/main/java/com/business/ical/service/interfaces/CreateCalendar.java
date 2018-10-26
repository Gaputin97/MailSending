package com.business.ical.service.interfaces;

import net.fortuna.ical4j.model.Calendar;

import java.net.URISyntaxException;
import java.text.ParseException;

public interface CreateCalendar {
    Calendar calendar() throws ParseException, URISyntaxException;
}
