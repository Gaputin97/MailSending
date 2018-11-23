package com.business.ical.service.interfaces;

import net.fortuna.ical4j.model.Calendar;

public interface ComplexEvents {
    Calendar complexEventInvite();
    Calendar complexEventUpdateAtLeastTwo();
    Calendar complexEventRescheduleAtLeastTwo();
    Calendar complexEventCancelAtLeastTwo();
}
