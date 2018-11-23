package com.business.ical.service.interfaces;

import net.fortuna.ical4j.model.Calendar;

public interface SimpleEvent {
    Calendar simpleEventInvitation();
    Calendar simpleEventUpdate();
    Calendar simpleEventReschedule();
    Calendar simpleEventCancel();
}
