package com.business.ical.service.interfaces;

import net.fortuna.ical4j.model.Calendar;

public interface ComplexUpdates {
    Calendar invitation();
    Calendar firstReschedule();

    Calendar firstInvite();
    Calendar secondReschedule();
}
