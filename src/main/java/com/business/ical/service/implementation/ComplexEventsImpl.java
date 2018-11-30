package com.business.ical.service.implementation;

import com.business.ical.service.interfaces.ComplexEvents;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.*;
import net.fortuna.ical4j.util.FixedUidGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.SocketException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Arrays;

@Component
public class ComplexEventsImpl implements ComplexEvents {
    private static final Logger logger = LoggerFactory.getLogger(ComplexEventsImpl.class);
    private Uid UID1;
    private Uid UID2;

    @Override
    public Calendar complexEventInvite() {
        Calendar complexEventInvitation = new Calendar();
        complexEventInvitation.getProperties().add(Version.VERSION_2_0);
        complexEventInvitation.getProperties().add(CalScale.GREGORIAN);
        complexEventInvitation.getProperties().add(new ProdId("-//Event Central//EN"));
        complexEventInvitation.getProperties().add(Method.PUBLISH);
        //***************************************************************************************************
        DateTime eventStartDateTime1 = null;
        DateTime eventEndDateTime1 = null;
        try {
            eventStartDateTime1 = new DateTime("20181120T130000Z");
            eventEndDateTime1 = new DateTime("20181120T152500Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event1 = new VEvent(eventStartDateTime1, eventEndDateTime1, "Invite Summary - 1");

        FixedUidGenerator fixedUidGenerator1 = null;
        try {
            event1.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
            fixedUidGenerator1 = new FixedUidGenerator("UHaputsin1");
        } catch (URISyntaxException | SocketException e) {
            e.printStackTrace();
        }
        UID1 = fixedUidGenerator1.generateUid();

        event1.getProperties().add(UID1);
        event1.getProperties().add(new Description("Create description for the first event"));
        event1.getProperties().add(new Sequence("0"));
        event1.getProperties().add(new Location("Conference room A103"));
        event1.getProperties().add(Transp.OPAQUE);
        PropertyList<Property> eventProperties1 = getEventProperties();
        event1.getProperties().addAll(eventProperties1);

        XProperty lotusNotesType1 = new XProperty("X-LOTUS-NOTICETYPE", "I");
        event1.getProperties().add(lotusNotesType1);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //***************************************************************************************************
        DateTime eventStartDateTime2 = null;
        DateTime eventEndDateTime2 = null;
        try {
            eventStartDateTime2 = new DateTime("20181123T170000Z");
            eventEndDateTime2 = new DateTime("20181123T182500Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event2 = new VEvent(eventStartDateTime2, eventEndDateTime2, "Invite Summary - 2");

        FixedUidGenerator fixedUidGenerator2 = null;
        try {
            event2.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
            fixedUidGenerator2 = new FixedUidGenerator("UHaputsin2");
        } catch (URISyntaxException | SocketException e) {
            e.printStackTrace();
        }
        UID2 = fixedUidGenerator2.generateUid();
        event2.getProperties().add(UID2);

        event2.getProperties().add(new Description("Create description for the second event"));
        event2.getProperties().add(new Sequence("0"));
        event2.getProperties().add(new Location("Conference room A103"));
        event2.getProperties().add(Transp.OPAQUE);
        PropertyList<Property> eventProperties2 = getEventProperties();
        event2.getProperties().addAll(eventProperties2);

        XProperty lotusNotesType2 = new XProperty("X-LOTUS-NOTICETYPE", "I");
        event2.getProperties().add(lotusNotesType2);

        //***************************************************************************************************
        complexEventInvitation.getComponents().add(event1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        complexEventInvitation.getComponents().add(event2);
        complexEventInvitation.validate();
        logger.info("\nCalendar: \n" + complexEventInvitation.toString());

        return complexEventInvitation;
    }

    @Override
    public Calendar complexEventUpdateAtLeastTwo() {
        Calendar complexEventInvitation = new Calendar();
        complexEventInvitation.getProperties().add(Version.VERSION_2_0);
        complexEventInvitation.getProperties().add(CalScale.GREGORIAN);
        complexEventInvitation.getProperties().add(new ProdId("-//Event Central//EN"));
        complexEventInvitation.getProperties().add(Method.PUBLISH);
        //***************************************************************************************************
        DateTime eventStartDateTime1 = null;
        DateTime eventEndDateTime1 = null;
        try {
            eventStartDateTime1 = new DateTime("20181120T130000Z");
            eventEndDateTime1 = new DateTime("20181120T152500Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event1 = new VEvent(eventStartDateTime1, eventEndDateTime1, "Update Summary - 1");
        event1.getProperties().add(new Description("Update description - 1"));
        event1.getProperties().add(new Sequence("0"));
        event1.getProperties().add(new Location("Conference room A104"));
        event1.getProperties().add(Transp.OPAQUE);
//        event1.getProperties().addAll(getEventProperties());

//        XProperty lotusNotesType1 = new XProperty("X-LOTUS-NOTICETYPE", "U");
//        event1.getProperties().add(lotusNotesType1);
//        XProperty lotusUpdateSeq = new XProperty("X-LOTUS-UPDATE-SEQ", "1");
//        event1.getProperties().add(lotusUpdateSeq);

        try {
            event1.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        event1.getProperties().add(UID1);
        //***************************************************************************************************
        DateTime eventStartDateTime2 = null;
        DateTime eventEndDateTime2 = null;
        try {
            eventStartDateTime2 = new DateTime("20181123T170000Z");
            eventEndDateTime2 = new DateTime("20181123T182500Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event2 = new VEvent(eventStartDateTime2, eventEndDateTime2, "Update Summary - 2");
        event2.getProperties().add(new Description("Update description - 2"));
        event2.getProperties().add(new Sequence("0"));
        event2.getProperties().add(new Location("Conference room A104"));
        event2.getProperties().add(Transp.OPAQUE);
//        event2.getProperties().addAll(getEventProperties());

//        XProperty lotusNotesType2 = new XProperty("X-LOTUS-NOTICETYPE", "U");
//        event2.getProperties().add(lotusNotesType2);
//        event2.getProperties().add(lotusUpdateSeq);

        try {
            event2.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        event2.getProperties().add(UID2);

        //***************************************************************************************************
        complexEventInvitation.getComponents().add(event1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        complexEventInvitation.getComponents().add(event2);
        complexEventInvitation.validate();
        logger.info("\nCalendar: \n" + complexEventInvitation.toString());

        return complexEventInvitation;
    }

    @Override
    public Calendar complexEventRescheduleAtLeastTwo() {
        return null;
    }

    @Override
    public Calendar complexEventCancelAtLeastTwo() {
        Calendar complexEventCancel = new Calendar();
        complexEventCancel.getProperties().add(Version.VERSION_2_0);
        complexEventCancel.getProperties().add(CalScale.GREGORIAN);
        complexEventCancel.getProperties().add(new ProdId("-//Event Central//EN"));
        complexEventCancel.getProperties().add(Method.PUBLISH);
        //***************************************************************************************************
        DateTime eventStartDateTime1 = null;
        DateTime eventEndDateTime1 = null;
        try {
            eventStartDateTime1 = new DateTime("20181120T130000Z");
            eventEndDateTime1 = new DateTime("20181120T152500Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event1 = new VEvent(eventStartDateTime1, eventEndDateTime1, "Cancel Summary - 1");

        XProperty lotusUpdateSeq = new XProperty("X-LOTUS-UPDATE-SEQ", "S");
        event1.getProperties().add(lotusUpdateSeq);

        event1.getProperties().add(UID1);
        try {
            event1.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        event1.getProperties().add(new Description("Canceling description for the first event"));
        event1.getProperties().add(new Sequence("1"));
        event1.getProperties().add(Status.VEVENT_CANCELLED);
        event1.getProperties().add(new Location("Conference room A103"));
        event1.getProperties().add(Transp.OPAQUE);
        PropertyList<Property> eventProperties1 = getEventProperties();
        event1.getProperties().addAll(eventProperties1);

        XProperty lotusNotesType1 = new XProperty("X-LOTUS-NOTICETYPE", "S");
        event1.getProperties().add(lotusNotesType1);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //***************************************************************************************************
        DateTime eventStartDateTime2 = null;
        DateTime eventEndDateTime2 = null;
        try {
            eventStartDateTime2 = new DateTime("20181123T170000Z");
            eventEndDateTime2 = new DateTime("20181123T182500Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event2 = new VEvent(eventStartDateTime2, eventEndDateTime2, "Cancel Summary - 2");

        event2.getProperties().add(lotusUpdateSeq);

        event2.getProperties().add(UID2);
        try {
            event2.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        event2.getProperties().add(new Description("Canceling description for the second event"));
        event2.getProperties().add(new Sequence("1"));
        event2.getProperties().add(new Location("Conference room A103"));
        event2.getProperties().add(Transp.OPAQUE);
        event2.getProperties().add(Status.VEVENT_CANCELLED);
        PropertyList<Property> eventProperties2 = getEventProperties();
        event2.getProperties().addAll(eventProperties2);

        XProperty lotusNotesType2 = new XProperty("X-LOTUS-NOTICETYPE", "S");
        event2.getProperties().add(lotusNotesType2);

        //***************************************************************************************************
        complexEventCancel.getComponents().add(event1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        complexEventCancel.getComponents().add(event2);
        complexEventCancel.validate();
        logger.info("\nCalendar: \n" + complexEventCancel.toString());

        return complexEventCancel;
    }

    private PropertyList<Property> getEventProperties () {
        PropertyList<Property> propertyList = new PropertyList<>();

        XProperty lotusBroadcast = new XProperty("X-LOTUS-BROADCAST", "TRUE");
        XProperty lotusPreventCounter = new XProperty("X-LOTUS-PREVENTCOUNTER", "FALSE");
        XProperty microsoftDisallowCounter = new XProperty("X-MICROSOFT-DISALLOW-COUNTER", "TRUE");
        XProperty outlookForceOpen = new XProperty("X-MS-OLK-FORCEINSPECTOROPEN", "TRUE");
        XProperty msAttachment = new XProperty("X-MS-ATTACHMENT", "YES");
        XProperty lotusUtf8 = new XProperty("X-LOTUS-CHARSET", "UTF-8");
        XProperty lotusPreventDelegation = new XProperty("X-LOTUS-PREVENTDELEGATION", "TRUE");
        XProperty lotusVersion = new XProperty("X-LOTUS-NOTESVERSION", "2");
        XProperty lotusAppType = new XProperty("X-LOTUS-APPTTYPE", "3");

        propertyList.addAll(Arrays.asList(lotusPreventDelegation,
                microsoftDisallowCounter, lotusVersion, lotusAppType, lotusBroadcast,
                lotusPreventCounter, lotusUtf8, outlookForceOpen, msAttachment));

        return propertyList;
    }
}
