package com.business.ical.service.interfaces;

import net.fortuna.ical4j.model.Calendar;

public interface ComplexUpdates {
    Calendar invitationAll();
    Calendar firstRescheduleAll();
    Calendar secondRescheduleAll();

    Calendar inviteOutlook();
    Calendar firstRescheduleOutlook();
    Calendar secondRescheduleOutlook();
}
