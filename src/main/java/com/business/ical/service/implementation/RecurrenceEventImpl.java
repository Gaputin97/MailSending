package com.business.ical.service.implementation;

import com.business.ical.service.interfaces.RecurrenceEvent;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.PartStat;
import net.fortuna.ical4j.model.parameter.Role;
import net.fortuna.ical4j.model.parameter.Rsvp;
import net.fortuna.ical4j.model.property.*;
import net.fortuna.ical4j.util.FixedUidGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.SocketException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RecurrenceEventImpl implements RecurrenceEvent {
    private static final Logger logger = LoggerFactory.getLogger(RecurrenceEvent.class);
    private Uid UID;

    @Override
    public Calendar recurOnePlusCancel() {
        return null;
    }

    @Override
    public Calendar recurInvitation(){
        Calendar recurrenceEventInvitation = new Calendar();
        recurrenceEventInvitation.getProperties().add(Version.VERSION_2_0);
        recurrenceEventInvitation.getProperties().add(CalScale.GREGORIAN);
        recurrenceEventInvitation.getProperties().add(new ProdId("-//Event Central//EN"));
        recurrenceEventInvitation.getProperties().add(Method.REQUEST);

        DateTime eventStartDateTime = null;
        DateTime eventEndDateTime = null;
        try {
            eventStartDateTime = new DateTime("20190115T130000Z");
            eventEndDateTime = new DateTime("20190115T152500Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event = new VEvent(eventStartDateTime, eventEndDateTime, "Send invitationAll to recurrence event");

        try {
            event.getProperties().add(new RRule("FREQ=DAILY;COUNT=1"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        event.getProperties().add(Transp.OPAQUE);
        event.getProperties().add(new Sequence("0"));

        FixedUidGenerator fixedUidGenerator = null;
        try {
            event.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
            event.getProperties().addAll(getAttendeeList());
            fixedUidGenerator = new FixedUidGenerator("UHaputsin");
        } catch (URISyntaxException | SocketException e) {
            logger.error("Error");
        }

        UID = new Uid(fixedUidGenerator.generateUid().toString());
        event.getProperties().add(UID);

        recurrenceEventInvitation.getComponents().add(event);
        recurrenceEventInvitation.validate();
        logger.info("\nCalendar: \n" + recurrenceEventInvitation.toString());
        return recurrenceEventInvitation;
    }

    @Override
    public Calendar recurUpdate() {
        Calendar recurrenceEventUpdate = new Calendar();
        recurrenceEventUpdate.getProperties().add(Version.VERSION_2_0);
        recurrenceEventUpdate.getProperties().add(CalScale.GREGORIAN);
        recurrenceEventUpdate.getProperties().add(new ProdId("-//Event Central//EN"));
        recurrenceEventUpdate.getProperties().add(Method.REQUEST);

        DateTime eventStartDateTime = null;
        DateTime eventEndDateTime = null;
        try {
            eventStartDateTime = new DateTime("20190112T130000Z");
            eventEndDateTime = new DateTime("20190112T152500Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event = new VEvent(eventStartDateTime, eventEndDateTime, "Updated event v1");

//        try {
//            event.getProperties().add(new RRule("FREQ=WEEKLY;INTERVAL=1;BYDAY=MO,WE,FR;COUNT=5"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

//        try {
//            event.getProperties().add(new RecurrenceId("20190111T130000Z"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        event.getProperties().add(Transp.OPAQUE);
        event.getProperties().add(new Sequence("1"));

        try {
            event.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
            event.getProperties().addAll(getAttendeeList());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        event.getProperties().add(UID);

        recurrenceEventUpdate.getComponents().add(event);
        recurrenceEventUpdate.validate();
        logger.info("\nCalendar: \n" + recurrenceEventUpdate.toString());
        return recurrenceEventUpdate;
    }

    @Override
    public Calendar recurSingleUpdate() {
        Calendar recurrenceSingleEventUpdate = new Calendar();
        recurrenceSingleEventUpdate.getProperties().add(Version.VERSION_2_0);
        recurrenceSingleEventUpdate.getProperties().add(CalScale.GREGORIAN);
        recurrenceSingleEventUpdate.getProperties().add(new ProdId("-//Event Central//EN"));
        recurrenceSingleEventUpdate.getProperties().add(Method.REQUEST);

        DateTime eventStartDateTime = null;
        DateTime eventEndDateTime = null;
        try {
            eventStartDateTime = new DateTime("20181121T13000Z");
            eventEndDateTime = new DateTime("20181121T152500Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event = new VEvent(eventStartDateTime, eventEndDateTime, "Updated event v2");

        try {
            event.getProperties().add(new RecurrenceId("20181121T13000Z"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        event.getProperties().add(Transp.OPAQUE);
        event.getProperties().add(new Sequence("0"));

        try {
            event.getProperties().addAll(getAttendeeList());
            event.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        event.getProperties().add(UID);

        recurrenceSingleEventUpdate.getComponents().add(event);
        recurrenceSingleEventUpdate.validate();
        logger.info("\nCalendar: \n" + recurrenceSingleEventUpdate.toString());
        return recurrenceSingleEventUpdate;
    }

    @Override
    public Calendar recurOnePlusUpdate() {
        Calendar recurrenceSingleEventUpdate = new Calendar();
        recurrenceSingleEventUpdate.getProperties().add(Version.VERSION_2_0);
        recurrenceSingleEventUpdate.getProperties().add(CalScale.GREGORIAN);
        recurrenceSingleEventUpdate.getProperties().add(new ProdId("-//Event Central//EN"));
        recurrenceSingleEventUpdate.getProperties().add(Method.REQUEST);

        DateTime eventStartDateTime = null;
        DateTime eventEndDateTime = null;
        try {
            eventStartDateTime = new DateTime("20181119T13000Z");
            eventEndDateTime = new DateTime("20181119T152500Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event = new VEvent(eventStartDateTime, eventEndDateTime, "Updated more than one event with different description");

        try {
            event.getProperties().add(new RRule("FREQ=WEEKLY;INTERVAL=1;BYDAY=MO,WE,FR;COUNT=5"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateList dates = new DateList();
        try {
            dates.add(new DateTime("20181119T13000Z"));
            dates.add(new DateTime("20181123T13000Z"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        event.getProperties().add(new ExDate(dates));
        event.getProperties().add(Transp.OPAQUE);
        event.getProperties().add(new Sequence("0"));

        try {
            event.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
            event.getProperties().addAll(getAttendeeList());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        event.getProperties().add(UID);

        recurrenceSingleEventUpdate.getComponents().add(event);
        recurrenceSingleEventUpdate.validate();
        logger.info("\nCalendar: \n" + recurrenceSingleEventUpdate.toString());
        return recurrenceSingleEventUpdate;
    }

    @Override
    public Calendar recurReschedule() {
        Calendar recurrenceEventReschedule = new Calendar();
        recurrenceEventReschedule.getProperties().add(Version.VERSION_2_0);
        recurrenceEventReschedule.getProperties().add(CalScale.GREGORIAN);
        recurrenceEventReschedule.getProperties().add(new ProdId("-//Event Central//EN"));
        recurrenceEventReschedule.getProperties().add(Method.REQUEST);

        DateTime eventStartDateTime = null;
        DateTime eventEndDateTime = null;
        try {
            eventStartDateTime = new DateTime("20190117T160000Z");
            eventEndDateTime = new DateTime("20190117T192500Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event = new VEvent(eventStartDateTime, eventEndDateTime, "Recurrence event with different TIME");

        try {
            event.getProperties().add(new RRule("FREQ=WEEKLY;INTERVAL=1;BYDAY=MO,FR;COUNT=7"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            event.getProperties().addAll(getAttendeeList());
            event.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        event.getProperties().add(UID);

        recurrenceEventReschedule.getComponents().add(event);
        recurrenceEventReschedule.validate();
        logger.info("\nCalendar: \n" + recurrenceEventReschedule.toString());
        return recurrenceEventReschedule;
    }

    @Override
    public Calendar recurSingleReschedule() {
        Calendar recurrenceSingleEventUpdate = new Calendar();
        recurrenceSingleEventUpdate.getProperties().add(Version.VERSION_2_0);
        recurrenceSingleEventUpdate.getProperties().add(CalScale.GREGORIAN);
        recurrenceSingleEventUpdate.getProperties().add(new ProdId("-//Event Central//EN"));
        recurrenceSingleEventUpdate.getProperties().add(Method.REQUEST);

        DateTime eventStartDateTime = null;
        DateTime eventEndDateTime = null;
        try {
            eventStartDateTime = new DateTime("20181123T16000Z");
            eventEndDateTime = new DateTime("20181123T202500Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event = new VEvent(eventStartDateTime, eventEndDateTime, "Reschedule single event ");

        try {
            event.getProperties().add(new RecurrenceId("20181123T15000Z"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        event.getProperties().add(Transp.OPAQUE);
        event.getProperties().add(new Sequence("3"));

        try {
            event.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
            event.getProperties().addAll(getAttendeeList());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        event.getProperties().add(UID);

        recurrenceSingleEventUpdate.getComponents().add(event);
        recurrenceSingleEventUpdate.validate();
        logger.info("\nCalendar: \n" + recurrenceSingleEventUpdate.toString());
        return recurrenceSingleEventUpdate;
    }

    @Override
    public Calendar recurOnePlusReschedule() {
        Calendar recurOnePlusReschedule = new Calendar();
        recurOnePlusReschedule.getProperties().add(Version.VERSION_2_0);
        recurOnePlusReschedule.getProperties().add(CalScale.GREGORIAN);
        recurOnePlusReschedule.getProperties().add(new ProdId("-//Event Central//EN"));
        recurOnePlusReschedule.getProperties().add(Method.REQUEST);

        DateTime eventStartDateTime = null;
        DateTime eventEndDateTime = null;
        try {
            eventStartDateTime = new DateTime("20181121T16000Z");
            eventEndDateTime = new DateTime("20181121T202500Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event = new VEvent(eventStartDateTime, eventEndDateTime, "Reschedule events with different description");


        try {
            event.getProperties().add(new RRule("FREQ=WEEKLY;INTERVAL=1;BYDAY=MO,WE,FR;COUNT=5"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateList dates = new DateList();
        try {
            dates.add(new DateTime("20181119T13000Z"));
            dates.add(new DateTime("20181123T13000Z"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        event.getProperties().add(Transp.OPAQUE);
        event.getProperties().add(new Sequence("4"));

        try {
            event.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
            event.getProperties().addAll(getAttendeeList());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        event.getProperties().add(new ExDate(dates));
        event.getProperties().add(UID);

        recurOnePlusReschedule.getComponents().add(event);
        recurOnePlusReschedule.validate();
        logger.info("\nCalendar: \n" + recurOnePlusReschedule.toString());
        return recurOnePlusReschedule;
    }

    @Override
    public Calendar recurCancel() {
        Calendar recurrenceEventCancel = new Calendar();
        recurrenceEventCancel.getProperties().add(Version.VERSION_2_0);
        recurrenceEventCancel.getProperties().add(CalScale.GREGORIAN);
        recurrenceEventCancel.getProperties().add(new ProdId("-//Event Central//EN"));
        recurrenceEventCancel.getProperties().add(Method.CANCEL);

        DateTime eventStartDateTime = null;
        DateTime eventEndDateTime = null;
        try {
            eventEndDateTime = new DateTime("20181116T152500Z");
            eventStartDateTime = new DateTime("20181116T13000Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event = new VEvent(eventStartDateTime, eventEndDateTime,  "Cancel recurrence event");

        try {
            event.getProperties().add(new RRule("FREQ=WEEKLY;INTERVAL=1;BYDAY=MO,WE,FR;COUNT=5"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        event.getProperties().add(Transp.OPAQUE);


        event.getProperties().add(new Sequence("4"));

        try {
            event.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
            event.getProperties().addAll(getAttendeeList());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        event.getProperties().add(Status.VEVENT_CANCELLED);

        event.getProperties().add(UID);

        recurrenceEventCancel.getComponents().add(event);
        recurrenceEventCancel.validate();
        logger.info("\nCalendar: \n" + recurrenceEventCancel.toString());
        return recurrenceEventCancel;
    }

    @Override
    public Calendar recurSingleCancel() {
        Calendar recurrenceEventCancel = new Calendar();
        recurrenceEventCancel.getProperties().add(Version.VERSION_2_0);
        recurrenceEventCancel.getProperties().add(CalScale.GREGORIAN);
        recurrenceEventCancel.getProperties().add(new ProdId("-//Event Central//EN"));
        recurrenceEventCancel.getProperties().add(Method.CANCEL);

        DateTime eventStartDateTime = null;
        DateTime eventEndDateTime = null;
        try {
            eventStartDateTime = new DateTime("20181116T13000Z");
            eventEndDateTime = new DateTime("20181116T152500Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event = new VEvent(eventStartDateTime, eventEndDateTime,  "Cancel one event from recurrence event");
        event.getProperties().add(Transp.OPAQUE);

        event.getProperties().add(new Sequence("4"));

        try {
            event.getProperties().add(new RecurrenceId("20181121T13000Z"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            event.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
            event.getProperties().addAll(getAttendeeList());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        event.getProperties().add(Clazz.PUBLIC);
        event.getProperties().add(Status.VEVENT_CANCELLED);

        event.getProperties().add(UID);

        recurrenceEventCancel.getComponents().add(event);
        recurrenceEventCancel.validate();
        logger.info("\nCalendar: \n" + recurrenceEventCancel.toString());
        return recurrenceEventCancel;
    }

    private List<Attendee> getAttendeeList() throws URISyntaxException {
        List<Attendee> attendeeList = new ArrayList<>();

        Attendee dev1 = new Attendee("gaputinseva@gmail.com");
        dev1.getParameters().add(Rsvp.FALSE);
        dev1.getParameters().add(PartStat.ACCEPTED);
        dev1.getParameters().add(Role.CHAIR);

        Attendee dev2 = new Attendee("gaputin@hotmail.com");
        dev2.getParameters().add(Rsvp.FALSE);
        dev2.getParameters().add(PartStat.NEEDS_ACTION);
        dev2.getParameters().add(Role.REQ_PARTICIPANT);

        Attendee dev3 = new Attendee("UHaputsin@ibagroup.eu");
        dev3.getParameters().add(Rsvp.FALSE);
        dev3.getParameters().add(PartStat.NEEDS_ACTION);
        dev3.getParameters().add(Role.REQ_PARTICIPANT);

        Attendee dev4 = new Attendee("gaputinsevaiba@gmail.com");
        dev4.getParameters().add(Rsvp.FALSE);
        dev4.getParameters().add(PartStat.NEEDS_ACTION);
        dev4.getParameters().add(Role.REQ_PARTICIPANT);

        attendeeList.add(dev1);
        attendeeList.add(dev2);
        attendeeList.add(dev3);
        attendeeList.add(dev4);


        return attendeeList;
    }
}
