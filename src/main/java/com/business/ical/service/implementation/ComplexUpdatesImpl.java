package com.business.ical.service.implementation;

import com.business.ical.service.interfaces.ComplexUpdates;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.*;
import net.fortuna.ical4j.util.FixedUidGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.SocketException;
import java.net.URISyntaxException;
import java.text.ParseException;

@Component
public class ComplexUpdatesImpl implements ComplexUpdates {
    private static final Logger logger = LoggerFactory.getLogger(EventWithHtmlImpl.class);
    private Uid Uid1;
    private Uid Uid2;

    {
        FixedUidGenerator fixedUidGenerator1 = null;
        FixedUidGenerator fixedUidGenerator2 = null;
        try {
            fixedUidGenerator1 = new FixedUidGenerator("haputsin");
            fixedUidGenerator2 = new FixedUidGenerator("Uhaputsin");
        } catch (SocketException e) {
            e.printStackTrace();
        }

        Uid1 = fixedUidGenerator1.generateUid();
        Uid2 = fixedUidGenerator2.generateUid();
    }

    @Override
    public Calendar invitationAll() {
        Calendar complexEventInvitation = new Calendar();
        complexEventInvitation.getProperties().add(Version.VERSION_2_0);
        complexEventInvitation.getProperties().add(CalScale.GREGORIAN);
        complexEventInvitation.getProperties().add(new ProdId("-//Event Central//EN"));
        complexEventInvitation.getProperties().add(Method.PUBLISH);
        //***************************************************************************************************
        DateTime eventStartDateTime1 = null;
        DateTime eventEndDateTime1 = null;
        try {
            eventStartDateTime1 = new DateTime("20190110T091100Z");
            eventEndDateTime1 = new DateTime("20190110T121100Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event11 = new VEvent(eventStartDateTime1, eventEndDateTime1, "Complex first event invitation summary");
        event11.getProperties().add(Transp.OPAQUE);

        event11.getProperties().add(new Sequence("0"));
        event11.getProperties().add(new Description("First event invitation description"));
        event11.getProperties().add(new Location("London"));

        event11.getProperties().add(Uid1);

        try {
            event11.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //***************************************************************************************************
        DateTime eventStartDateTime2 = null;
        DateTime eventEndDateTime2 = null;
        try {
            eventStartDateTime2 = new DateTime("20190112T091100Z");
            eventEndDateTime2 = new DateTime("20190112T121100Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event12 = new VEvent(eventStartDateTime2, eventEndDateTime2, "Complex second event invitation summary");
        event12.getProperties().add(Transp.OPAQUE);

        event12.getProperties().add(new Sequence("0"));
        event12.getProperties().add(new Description("Second event invitation description"));
        event12.getProperties().add(new Location("Moscow"));

        event12.getProperties().add(Uid2);

        try {
            event12.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        complexEventInvitation.getComponents().add(event11);
        complexEventInvitation.getComponents().add(event12);

        complexEventInvitation.validate();
        logger.info("\nCalendar: \n" + complexEventInvitation.toString());

        return complexEventInvitation;
    }

    @Override
    public Calendar firstRescheduleAll() {
        Calendar complexEventReschedule = new Calendar();
        complexEventReschedule.getProperties().add(Version.VERSION_2_0);
        complexEventReschedule.getProperties().add(CalScale.GREGORIAN);
        complexEventReschedule.getProperties().add(new ProdId("-//Event Central//EN"));
        complexEventReschedule.getProperties().add(Method.PUBLISH);

        //***************************************************************************************************
        DateTime eventStartDateTime12 = null;
        DateTime eventEndDateTime12 = null;

        try {
            eventStartDateTime12 = new DateTime("20190111T092200Z");
            eventEndDateTime12 = new DateTime("20190111T122200Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event12 = new VEvent(eventStartDateTime12, eventEndDateTime12, "First rescheduling of the first event summary");
        event12.getProperties().add(Transp.TRANSPARENT);

        event12.getProperties().add(new Sequence("1"));
        event12.getProperties().add(new Description("First rescheduling of the first event description"));
        event12.getProperties().add(new Location("Pacino"));
        event12.getProperties().add(Uid1);

        try {
            event12.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //***************************************************************************************************
        DateTime eventStartDateTime22 = null;
        DateTime eventEndDateTime22 = null;

        try {
            eventStartDateTime22 = new DateTime("20190113T092200Z");
            eventEndDateTime22 = new DateTime("20190113T122200Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event22 = new VEvent(eventStartDateTime22, eventEndDateTime22, "First rescheduling of the second event summary");
        event22.getProperties().add(Transp.TRANSPARENT);

        event22.getProperties().add(new Sequence("1"));
        event22.getProperties().add(new Description("First rescheduling of the second event description"));
        event22.getProperties().add(new Location("Miami"));
        event22.getProperties().add(Uid2);

        try {
            event22.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        complexEventReschedule.getComponents().add(event12);
        complexEventReschedule.getComponents().add(event22);

        complexEventReschedule.validate();
        logger.info("\nCalendar: \n" + complexEventReschedule.toString());

        return complexEventReschedule;
    }

    @Override
    public Calendar secondRescheduleAll() {
        Calendar secondComplexRescheduleCalendar = new Calendar();
        secondComplexRescheduleCalendar.getProperties().add(Version.VERSION_2_0);
        secondComplexRescheduleCalendar.getProperties().add(CalScale.GREGORIAN);
        secondComplexRescheduleCalendar.getProperties().add(new ProdId("-//Event Central//EN"));
        secondComplexRescheduleCalendar.getProperties().add(Method.PUBLISH);

        //***************************************************************************************************
        DateTime eventStartDateTime13 = null;
        DateTime eventEndDateTime13 = null;

        try {
            eventStartDateTime13 = new DateTime("20181203T143300Z");
            eventEndDateTime13 = new DateTime("20181203T173300Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event13 = new VEvent(eventStartDateTime13, eventEndDateTime13, "Second rescheduling of the first event summary");
        event13.getProperties().add(Transp.TRANSPARENT);

        event13.getProperties().add(new Sequence("2"));
        event13.getProperties().add(new Description("Second rescheduling of the first event description"));
        event13.getProperties().add(Uid1);

        try {
            event13.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //***************************************************************************************************
        DateTime eventStartDateTime23 = null;
        DateTime eventEndDateTime23 = null;

        try {
            eventStartDateTime23 = new DateTime("20181207T143300Z");
            eventEndDateTime23 = new DateTime("20181207T183300Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event23 = new VEvent(eventStartDateTime23, eventEndDateTime23, "Second rescheduling of the second event summary");
        event23.getProperties().add(Transp.TRANSPARENT);

        event23.getProperties().add(new Sequence("2"));
        event23.getProperties().add(new Description("Second rescheduling of the second event description"));
        event23.getProperties().add(Uid2);

        try {
            event23.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        secondComplexRescheduleCalendar.getComponents().add(event13);
        secondComplexRescheduleCalendar.getComponents().add(event23);

        secondComplexRescheduleCalendar.validate();
        logger.info("\nCalendar: \n" + secondComplexRescheduleCalendar.toString());

        return secondComplexRescheduleCalendar;
    }

    @Override
    public Calendar inviteOutlook() {
        Calendar complexRecurrenceOutlookInvitation = new Calendar();
        complexRecurrenceOutlookInvitation.getProperties().add(Version.VERSION_2_0);
        complexRecurrenceOutlookInvitation.getProperties().add(CalScale.GREGORIAN);
        complexRecurrenceOutlookInvitation.getProperties().add(new ProdId("-//Event Central//EN"));
        complexRecurrenceOutlookInvitation.getProperties().add(Method.PUBLISH);

        //***************************************************************************************************
        DateTime eventStartDateTime1 = null;
        DateTime eventEndDateTime1 = null;
        try {
            eventStartDateTime1 = new DateTime("20181201T131100Z");
            eventEndDateTime1 = new DateTime("20181201T151100Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event1 = new VEvent(eventStartDateTime1, eventEndDateTime1, "Complex first event inviting to Outlook using RRule");

        try {
            event1.getProperties().add(new RRule("FREQ=DAILY;COUNT=1"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        event1.getProperties().add(Transp.OPAQUE);
        event1.getProperties().add(new Sequence("0"));
        event1.getProperties().add(Uid1);

        try {
            event1.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            logger.error("Some error depends to organizer");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //***************************************************************************************************
        DateTime eventStartDateTime2 = null;
        DateTime eventEndDateTime2 = null;
        try {
            eventStartDateTime2 = new DateTime("20181205T131100Z");
            eventEndDateTime2 = new DateTime("20181205T151100Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event2 = new VEvent(eventStartDateTime2, eventEndDateTime2, "Complex second event inviting to Outlook using RRule");

        try {
            event2.getProperties().add(new RRule("FREQ=DAILY;COUNT=1"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        event2.getProperties().add(Transp.OPAQUE);
        event2.getProperties().add(new Sequence("0"));
        event2.getProperties().add(Uid2);

        try {
            event2.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            logger.error("Error has cause with creating a new UID1");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        complexRecurrenceOutlookInvitation.getComponents().add(event1);
//        complexRecurrenceOutlookInvitation.getComponents().add(event2);

        logger.info("\nCalendar: \n" + complexRecurrenceOutlookInvitation.toString());
        complexRecurrenceOutlookInvitation.validate();
        return complexRecurrenceOutlookInvitation;
    }

    @Override
    public Calendar firstRescheduleOutlook() {
        Calendar firstRecurrenceComplexReschedule = new Calendar();
        firstRecurrenceComplexReschedule.getProperties().add(Version.VERSION_2_0);
        firstRecurrenceComplexReschedule.getProperties().add(CalScale.GREGORIAN);
        firstRecurrenceComplexReschedule.getProperties().add(new ProdId("-//Event Central//EN"));
        firstRecurrenceComplexReschedule.getProperties().add(Method.PUBLISH);

        //***************************************************************************************************
        DateTime eventStartDateTime11 = null;
        DateTime eventEndDateTime11 = null;
        try {
            eventStartDateTime11 = new DateTime("20181201T131100Z");
            eventEndDateTime11 = new DateTime("20181201T151100Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event11 = new VEvent(eventStartDateTime11, eventEndDateTime11, "Complex first event inviting to Outlook using RRule");

        try {
            event11.getProperties().add(new RRule("FREQ=DAILY;COUNT=0"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        event11.getProperties().add(Transp.OPAQUE);
        event11.getProperties().add(new Sequence("1"));
        event11.getProperties().add(Uid1);

        try {
            event11.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            logger.error("Some error depends to organizer");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //***************************************************************************************************
        DateTime eventStartDateTime12 = null;
        DateTime eventEndDateTime12 = null;
        try {
            eventStartDateTime12 = new DateTime("20181202T122200Z");
            eventEndDateTime12 = new DateTime("20181202T162200Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event12 = new VEvent(eventStartDateTime12, eventEndDateTime12, "Rescheduled first event");

        try {
            event12.getProperties().add(new RRule("FREQ=DAILY;COUNT=1"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        event12.getProperties().add(new RecurrenceId(eventStartDateTime11));
        event12.getProperties().add(Transp.OPAQUE);
        event12.getProperties().add(new Sequence("2"));
        event12.getProperties().add(Uid1);

        try {
            event12.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            logger.error("Some error depends to organizer");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //***************************************************************************************************
        //===================================================================================================
        //***************************************************************************************************
//        DateTime eventStartDateTime21 = null;
//        DateTime eventEndDateTime21 = null;
//        try {
//            eventStartDateTime21 = new DateTime("20181205T131100Z");
//            eventEndDateTime21 = new DateTime("20181205T151100Z");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        VEvent event21 = new VEvent(eventStartDateTime21, eventEndDateTime21, "Complex second event inviting to Outlook using RRule");
//
//        try {
//            event21.getProperties().add(new RRule("FREQ=DAILY;COUNT=1"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        DateList dates2 = new DateList();
//        try {
//            dates2.add(new DateTime("20181205T131100Z"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        event12.getProperties().add(new ExDate(dates2));
//        event21.getProperties().add(Transp.OPAQUE);
//        event21.getProperties().add(new Sequence("1"));
//        event21.getProperties().add(Uid2);
//
//        try {
//            event21.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
//        } catch (URISyntaxException e) {
//            logger.error("Error has cause with creating a new UID1");
//        }
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        //***************************************************************************************************
//        DateTime eventStartDateTime22 = null;
//        DateTime eventEndDateTime22 = null;
//        try {
//            eventStartDateTime22 = new DateTime("20181206T152200Z");
//            eventEndDateTime22 = new DateTime("20181206T172200Z");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        VEvent event22 = new VEvent(eventStartDateTime22, eventEndDateTime22, "Rescheduled second event");
//
//        try {
//            event22.getProperties().add(new RRule("FREQ=DAILY;COUNT=1"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        event22.getProperties().add(Transp.OPAQUE);
//        event22.getProperties().add(new Sequence("2"));
//        event22.getProperties().add(Uid2);
//
//        try {
//            event22.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
//        } catch (URISyntaxException e) {
//            logger.error("Error has cause with creating a new UID1");
//        }

        firstRecurrenceComplexReschedule.getComponents().add(event11);
        firstRecurrenceComplexReschedule.getComponents().add(event12);
//        firstRecurrenceComplexReschedule.getComponents().add(event21);
//        firstRecurrenceComplexReschedule.getComponents().add(event22);

        logger.info("\nCalendar: \n" + firstRecurrenceComplexReschedule.toString());
        firstRecurrenceComplexReschedule.validate();
        return firstRecurrenceComplexReschedule;
    }

    @Override
    public Calendar secondRescheduleOutlook() {
        Calendar secondRescheduleInvitation = new Calendar();
        secondRescheduleInvitation.getProperties().add(Version.VERSION_2_0);
        secondRescheduleInvitation.getProperties().add(CalScale.GREGORIAN);
        secondRescheduleInvitation.getProperties().add(new ProdId("-//Event Central//EN"));
        secondRescheduleInvitation.getProperties().add(Method.PUBLISH);

        //***************************************************************************************************
        DateTime eventStartDateTime11 = null;
        DateTime eventEndDateTime11 = null;
        try {
            eventStartDateTime11 = new DateTime("20181201T131100Z");
            eventEndDateTime11 = new DateTime("20181201T151100Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event11 = new VEvent(eventStartDateTime11, eventEndDateTime11, "Send complex invitations to Outlook client");

        try {
            event11.getProperties().add(new RRule("FREQ=DAILY;COUNT=1"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateList dates = new DateList();
        dates.add(eventStartDateTime11);
        event11.getProperties().add(new ExDate(dates));

        event11.getProperties().add(Transp.OPAQUE);
        event11.getProperties().add(new Sequence("2"));
        event11.getProperties().add(Uid1);

        try {
            event11.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            logger.error("Error has cause with creating a new UID1");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //***************************************************************************************************
        DateTime eventStartDateTime12 = null;
        DateTime eventEndDateTime12 = null;
        try {
            eventStartDateTime12 = new DateTime("20181202T142200Z");
            eventEndDateTime12 = new DateTime("20181202T162200Z");
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        VEvent event12 = new VEvent(eventStartDateTime12, eventEndDateTime12, "Send first reschedule to complex event");

        try {
            event12.getProperties().add(new RRule("FREQ=DAILY;COUNT=1"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateList dates1 = new DateList();
        dates1.add(eventStartDateTime12);
        event12.getProperties().add(new ExDate(dates1));

        event12.getProperties().add(Transp.OPAQUE);
        event12.getProperties().add(new Sequence("3"));
        event12.getProperties().add(Uid1);

        try {
            event12.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //***************************************************************************************************
        DateTime eventStartDateTime13 = null;
        DateTime eventEndDateTime13 = null;
        try {
            eventStartDateTime13 = new DateTime("20181203T133300Z");
            eventEndDateTime13 = new DateTime("20181203T153300Z");
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        VEvent event13 = new VEvent(eventStartDateTime13, eventEndDateTime13, "Send second reschedule to complex(recur) event");

//        event13.getProperties().add(new RecurrenceId(eventStartDateTime12));
        try {
            event13.getProperties().add(new RRule("FREQ=DAILY;COUNT=1"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        event13.getProperties().add(Transp.OPAQUE);
        event13.getProperties().add(new Sequence("4"));
        event13.getProperties().add(Uid1);

        try {
            event13.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        secondRescheduleInvitation.getComponents().add(event11);
        secondRescheduleInvitation.getComponents().add(event12);
        secondRescheduleInvitation.getComponents().add(event13);

        secondRescheduleInvitation.validate();
        logger.info("\nCalendar: \n" + secondRescheduleInvitation.toString());
        return secondRescheduleInvitation;
    }
}

