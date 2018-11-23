package com.business.ical.service.interfaces;

import net.fortuna.ical4j.model.Calendar;

public interface RecurrenceEvent {
    Calendar recurInvitation();

    Calendar recurUpdate();
    Calendar recurSingleUpdate();
    Calendar recurOnePlusUpdate();

    Calendar recurReschedule();
    Calendar recurSingleReschedule();
    Calendar recurOnePlusReschedule();

    Calendar recurCancel();
    Calendar recurSingleCancel();
    Calendar recurOnePlusCancel();
}
