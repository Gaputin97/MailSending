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

    private Uid UID = null;

    private Uid generate() {

        FixedUidGenerator fixedUidGenerator = null;
        try {
            fixedUidGenerator = new FixedUidGenerator("haputsin");
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return fixedUidGenerator.generateUid();
    }

    @Override
    public Calendar invitation() {

        Calendar complexEventCancel = new Calendar();
        complexEventCancel.getProperties().add(Version.VERSION_2_0);
        complexEventCancel.getProperties().add(CalScale.GREGORIAN);
        complexEventCancel.getProperties().add(new ProdId("-//Event Central//EN"));
        complexEventCancel.getProperties().add(Method.PUBLISH);
        //***************************************************************************************************
        DateTime eventStartDateTime1 = null;
        DateTime eventEndDateTime1 = null;
        try {
            eventStartDateTime1 = new DateTime("20181201T091100Z");
            eventEndDateTime1 = new DateTime("20181201T121100Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event1 = new VEvent(eventStartDateTime1, eventEndDateTime1, "Event invitation");
        event1.getProperties().add(Transp.OPAQUE);

        event1.getProperties().add(new Sequence("0"));
        event1.getProperties().add(new Description("Invite description for event"));
        event1.getProperties().add(new Location("London"));
        UID = generate();
        event1.getProperties().add(UID);
        try {
            event1.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
//        PropertyList<Property> eventProperties1 = getEventProperties();
//        event1.getProperties().addAll(eventProperties1);

        XProperty lotusNotesType1 = new XProperty("X-LOTUS-NOTICETYPE", "I");
        event1.getProperties().add(lotusNotesType1);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        complexEventCancel.getComponents().add(event1);
        complexEventCancel.validate();
        logger.info("\nCalendar: \n" + complexEventCancel.toString());

        return complexEventCancel;
    }

    @Override
    public Calendar firstReschedule() {

        Calendar complexEventReschedule = new Calendar();
        complexEventReschedule.getProperties().add(Version.VERSION_2_0);
        complexEventReschedule.getProperties().add(CalScale.GREGORIAN);
        complexEventReschedule.getProperties().add(new ProdId("-//Event Central//EN"));
        complexEventReschedule.getProperties().add(Method.PUBLISH);

        //***************************************************************************************************
        DateTime eventStartDateTime1 = null;
        DateTime eventEndDateTime1 = null;
        try {
            eventStartDateTime1 = new DateTime("20181201T091100Z");
            eventEndDateTime1 = new DateTime("20181201T121100Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event1 = new VEvent(eventStartDateTime1, eventEndDateTime1, "Event invitation");
        event1.getProperties().add(Transp.OPAQUE);

        event1.getProperties().add(new Sequence("0"));
        event1.getProperties().add(new Description("Invite description for event"));
        event1.getProperties().add(new Location("London"));

        if (UID != null) {
            event1.getProperties().add(UID);
        } else {
            UID = generate();
            event1.getProperties().add(UID);
        }

        try {
            event1.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        XProperty outlookForceOpen = new XProperty("X-MS-OLK-FORCEINSPECTOROPEN", "TRUE");
        event1.getProperties().add(outlookForceOpen);
        //***************************************************************************************************
        DateTime eventStartDateTime2 = null;
        DateTime eventEndDateTime2 = null;

        try {
            eventStartDateTime2 = new DateTime("20181202T122200Z");
            eventEndDateTime2 = new DateTime("20181202T152200Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event2 = new VEvent(eventStartDateTime2, eventEndDateTime2, "First reschedule summary - 1");
        event2.getProperties().add(Transp.TRANSPARENT);

        event2.getProperties().add(new Sequence("1"));
        event2.getProperties().add(new Description("First reschedule decs 1"));
        event2.getProperties().add(UID);

        try {
            event2.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        event2.getProperties().add(outlookForceOpen);

        //***************************************************************************************************
        complexEventReschedule.getComponents().add(event1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        complexEventReschedule.getComponents().add(event2);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        complexEventReschedule.validate();
        logger.info("\nCalendar: \n" + complexEventReschedule.toString());

        return complexEventReschedule;
    }

    @Override
    public Calendar firstInvite() {
        Calendar recurrenceEventInvitation = new Calendar();
        recurrenceEventInvitation.getProperties().add(Version.VERSION_2_0);
        recurrenceEventInvitation.getProperties().add(CalScale.GREGORIAN);
        recurrenceEventInvitation.getProperties().add(new ProdId("-//Event Central//EN"));
        recurrenceEventInvitation.getProperties().add(Method.PUBLISH);

        DateTime eventStartDateTime = null;
        DateTime eventEndDateTime = null;
        try {
            eventStartDateTime = new DateTime("20181201T130000Z");
            eventEndDateTime = new DateTime("20181201T152500Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event = new VEvent(eventStartDateTime, eventEndDateTime, "Send invitation to complex(recur) event");

        try {
            event.getProperties().add(new RRule("FREQ=DAILY;COUNT=1"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        event.getProperties().add(Transp.OPAQUE);
        event.getProperties().add(new Sequence("0"));

        try {
            event.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            logger.error("Error has cause with creating a new UID");
        }

        if (UID != null) {
            event.getProperties().add(UID);
        } else {
            UID = generate();
            event.getProperties().add(UID);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        recurrenceEventInvitation.getComponents().add(event);
        logger.info("\nCalendar: \n" + recurrenceEventInvitation.toString());
        recurrenceEventInvitation.validate();
        return recurrenceEventInvitation;
    }

    @Override
    public Calendar secondReschedule() {
        Calendar recurrenceEventInvitation = new Calendar();
        recurrenceEventInvitation.getProperties().add(Version.VERSION_2_0);
        recurrenceEventInvitation.getProperties().add(CalScale.GREGORIAN);
        recurrenceEventInvitation.getProperties().add(new ProdId("-//Event Central//EN"));
        recurrenceEventInvitation.getProperties().add(Method.PUBLISH);

        DateTime eventStartDateTime = null;
        DateTime eventEndDateTime = null;
        try {
            eventStartDateTime = new DateTime("20181201T130000Z");
            eventEndDateTime = new DateTime("20181201T152500Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event = new VEvent(eventStartDateTime, eventEndDateTime, "Send invitation to complex(recur) event");

        try {
            event.getProperties().add(new RRule("FREQ=DAILY;COUNT=1"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateList dates = new DateList();
        try {
            dates.add(new DateTime("20181201T130000Z"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        event.getProperties().add(new ExDate(dates));
        event.getProperties().add(Transp.OPAQUE);
        event.getProperties().add(new Sequence("1"));

        FixedUidGenerator fixedUidGenerator = null;
        try {
            event.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
            fixedUidGenerator = new FixedUidGenerator("UHaputsin");
        } catch (URISyntaxException | SocketException e) {
            logger.error("Error has cause with creating a new UID");
        }
        UID = fixedUidGenerator.generateUid();
        event.getProperties().add(UID);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //***************************************************************************************************
        DateTime eventStartDateTime2 = null;
        DateTime eventEndDateTime2 = null;
        try {
            eventStartDateTime2 = new DateTime("20181202T132200Z");
            eventEndDateTime2 = new DateTime("20181202T152200Z");
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        VEvent event2 = new VEvent(eventStartDateTime2, eventEndDateTime2, "Send reschedule to complex(recur) event");

        try {
            event2.getProperties().add(new RRule("FREQ=DAILY;COUNT=1"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateList dates1 = new DateList();
        try {
            dates1.add(new DateTime("20181202T132200Z"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        event2.getProperties().add(new ExDate(dates1));
        event2.getProperties().add(Transp.OPAQUE);
        event2.getProperties().add(new Sequence("2"));

        try {
            event2.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }

        event2.getProperties().add(UID);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //***************************************************************************************************
        DateTime eventStartDateTime3 = null;
        DateTime eventEndDateTime3 = null;
        try {
            eventStartDateTime3 = new DateTime("20181203T133300Z");
            eventEndDateTime3 = new DateTime("20181203T153300Z");
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        VEvent event3 = new VEvent(eventStartDateTime3, eventEndDateTime3, "Send second reschedule to complex(recur) event");

        event3.getProperties().add(new RecurrenceId(eventStartDateTime2));
        event3.getProperties().add(Transp.OPAQUE);
        event3.getProperties().add(new Sequence("3"));

        try {
            event3.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }

        event3.getProperties().add(UID);
        recurrenceEventInvitation.getComponents().add(event);
        recurrenceEventInvitation.getComponents().add(event2);
        recurrenceEventInvitation.getComponents().add(event3);

        recurrenceEventInvitation.validate();
        logger.info("\nCalendar: \n" + recurrenceEventInvitation.toString());
        return recurrenceEventInvitation;
    }

    //    private PropertyList<Property> getEventProperties() {
//        PropertyList<Property> propertyList = new PropertyList<>();
//
//        XProperty lotusBroadcast = new XProperty("X-LOTUS-BROADCAST", "TRUE");
//        XProperty lotusPreventCounter = new XProperty("X-LOTUS-PREVENTCOUNTER", "FALSE");
//        XProperty microsoftDisallowCounter = new XProperty("X-MICROSOFT-DISALLOW-COUNTER", "TRUE");
//        XProperty msAttachment = new XProperty("X-MS-ATTACHMENT", "YES");
//        XProperty lotusUtf8 = new XProperty("X-LOTUS-CHARSET", "UTF-8");
//        XProperty lotusPreventDelegation = new XProperty("X-LOTUS-PREVENTDELEGATION", "TRUE");
//        XProperty lotusVersion = new XProperty("X-LOTUS-NOTESVERSION", "2");
//        XProperty lotusAppType = new XProperty("X-LOTUS-APPTTYPE", "3");
//
//        propertyList.addAll(Arrays.asList(lotusPreventDelegation,
//                microsoftDisallowCounter, lotusVersion, lotusAppType, lotusBroadcast,
//                lotusPreventCounter, lotusUtf8, outlookForceOpen, msAttachment));
//        return propertyList;
//    }
}

