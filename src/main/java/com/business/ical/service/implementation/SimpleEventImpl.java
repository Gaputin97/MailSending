package com.business.ical.service.implementation;

import com.business.ical.service.interfaces.SimpleEvent;
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
public class SimpleEventImpl implements SimpleEvent {
    private static final Logger logger = LoggerFactory.getLogger(SimpleEventImpl.class);
    private Uid UID;

    @Override
    public Calendar simpleEventInvitation() {
        Calendar simpleEventInvitation = new Calendar();
        simpleEventInvitation.getProperties().add(Version.VERSION_2_0);
        simpleEventInvitation.getProperties().add(CalScale.GREGORIAN);
        simpleEventInvitation.getProperties().add(new ProdId("-//Event Central//EN"));
        simpleEventInvitation.getProperties().add(Method.REQUEST);

        DateTime eventStartDateTime = null;
        DateTime eventEndDateTime = null;
        try {
            eventStartDateTime = new DateTime("20181118T130000Z");
            eventEndDateTime = new DateTime("20181118T152500Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event = new VEvent(eventStartDateTime, eventEndDateTime, "Invite Summary");
        event.getProperties().add(new Description("Create description"));
        event.getProperties().add(new Sequence("0"));
//        event.getProperties().add(Status.VEVENT_CONFIRMED);
//        event.getProperties().add(Transp.OPAQUE);
        PropertyList<Property> eventProperties = getEventProperties();
        event.getProperties().addAll(eventProperties);

        XProperty lotusNotesType = new XProperty("X-LOTUS-NOTICETYPE", "I");
        event.getProperties().add(lotusNotesType);

        FixedUidGenerator fixedUidGenerator = null;
        try {
            event.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
            event.getProperties().addAll(getAttendeeList());
            fixedUidGenerator = new FixedUidGenerator("UHaputsin");
        } catch (URISyntaxException | SocketException e) {
            e.printStackTrace();
        }

        UID = fixedUidGenerator.generateUid();
        event.getProperties().add(UID);

        simpleEventInvitation.getComponents().add(event);
        simpleEventInvitation.validate();
        logger.info("\nCalendar: \n" + simpleEventInvitation.toString());
        return simpleEventInvitation;
    }

    @Override
    public Calendar simpleEventUpdate() {
        Calendar simpleEventUpdate = new Calendar();
        simpleEventUpdate.getProperties().add(Version.VERSION_2_0);
        simpleEventUpdate.getProperties().add(CalScale.GREGORIAN);
        simpleEventUpdate.getProperties().add(new ProdId("-//Event Central//EN"));
        simpleEventUpdate.getProperties().add(Method.REQUEST);

        DateTime eventStartDateTime = null;
        DateTime eventEndDateTime = null;
        try {
            eventStartDateTime = new DateTime("20181118T130000Z");
            eventEndDateTime = new DateTime("20181118T152500Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event = new VEvent(eventStartDateTime, eventEndDateTime, "Update summary");
        event.getProperties().add(new Description("Update Description"));
        event.getProperties().add(new Sequence("0"));
//        event.getProperties().add(Transp.OPAQUE);
//        event.getProperties().add(Status.VEVENT_CONFIRMED);
        PropertyList<Property> eventProperties = getEventProperties();
        event.getProperties().addAll(eventProperties);

        XProperty lotusNotesType = new XProperty("X-LOTUS-NOTICETYPE", "E");
        XProperty subjectUpdate = new XProperty("X-LOTUS-UPDATE-SUBJECT", "New Subject For Lotus");
        XProperty sequenceUpdate = new XProperty("X-LOTUS-UPDATE-SEQ", "1");
        event.getProperties().add(subjectUpdate);
        event.getProperties().add(sequenceUpdate);
        event.getProperties().add(lotusNotesType);

        try {
            event.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
            event.getProperties().addAll(getAttendeeList());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        event.getProperties().add(UID);

        simpleEventUpdate.getComponents().add(event);
        simpleEventUpdate.validate();
        logger.info("\nCalendar: \n" + simpleEventUpdate.toString());
        return simpleEventUpdate;
    }

    @Override
    public Calendar simpleEventReschedule() {
        Calendar simpleEventReschedule = new Calendar();
        simpleEventReschedule.getProperties().add(Version.VERSION_2_0);
        simpleEventReschedule.getProperties().add(CalScale.GREGORIAN);
        simpleEventReschedule.getProperties().add(new ProdId("-//Event Central//EN"));
        simpleEventReschedule.getProperties().add(Method.REQUEST);

        DateTime eventStartDateTime = null;
        DateTime eventEndDateTime = null;
        try {
            eventStartDateTime = new DateTime("20181120T11000Z");
            eventEndDateTime = new DateTime("20181120T132500Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event = new VEvent(eventStartDateTime, eventEndDateTime, "Reschedule summary");
        event.getProperties().add(new Description("Reschedule description"));

        PropertyList<Property> eventProperties = getEventProperties();
        event.getProperties().addAll(eventProperties);
        event.getProperties().add(Transp.OPAQUE);

        XProperty lotusNotesType = new XProperty("X-LOTUS-NOTICETYPE", "U");
        event.getProperties().add(lotusNotesType);
        event.getProperties().add(new Sequence("2"));

        try {
            event.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
            event.getProperties().addAll(getAttendeeList());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        XProperty sequenceUpdate = new XProperty("X-LOTUS-UPDATE-SEQ", "2");
        event.getProperties().add(sequenceUpdate);

        event.getProperties().add(UID);

        simpleEventReschedule.getComponents().add(event);
        simpleEventReschedule.validate();
        logger.info("\nCalendar: \n" + simpleEventReschedule.toString());
        return simpleEventReschedule;
    }

    @Override
    public Calendar simpleEventCancel() {
        Calendar calendar = new Calendar();
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);
        calendar.getProperties().add(new ProdId("-//Event Central//EN"));
        calendar.getProperties().add(Method.CANCEL);

        DateTime eventStartDateTime = null;
        DateTime eventEndDateTime = null;
        try {
            eventStartDateTime = new DateTime("20181120T11000Z");
            eventEndDateTime = new DateTime("20181120T132500Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        VEvent event = new VEvent(eventStartDateTime, eventEndDateTime,  "Canceled summary");
        event.getProperties().add(new Description("Cancel description"));
        event.getProperties().add(Transp.OPAQUE);

        PropertyList<Property> eventProperties = getEventProperties();
        event.getProperties().addAll(eventProperties);
        XProperty lotusNotesType = new XProperty("X-LOTUS-NOTICETYPE", "S");
        event.getProperties().add(lotusNotesType);
        event.getProperties().add(new Sequence("3"));

        try {
            event.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
            event.getProperties().addAll(getAttendeeList());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        event.getProperties().add(Clazz.PUBLIC);
        event.getProperties().add(Status.VEVENT_CANCELLED);

        event.getProperties().add(UID);

        calendar.getComponents().add(event);
        calendar.validate();
        logger.info("\nCalendar: \n" + calendar.toString());
        return calendar;
    }

    private PropertyList<Property> getEventProperties () {
        PropertyList<Property> propertyList = new PropertyList<>();

        Location locationProperty = new Location("Conference room A103");

        XProperty lotusBroadcast = new XProperty("X-LOTUS-BROADCAST", "TRUE");
        XProperty lotusPreventCounter = new XProperty("X-LOTUS-PREVENTCOUNTER", "FALSE");
        XProperty microsoftDisallowCounter = new XProperty("X-MICROSOFT-DISALLOW-COUNTER", "TRUE");
        XProperty outlookForceOpen = new XProperty("X-MS-OLK-FORCEINSPECTOROPEN", "TRUE");
        XProperty msAttachment = new XProperty("X-MS-ATTACHMENT", "YES");
        XProperty lotusUtf8 = new XProperty("X-LOTUS-CHARSET", "UTF-8");
        XProperty lotusPreventDelegation = new XProperty("X-LOTUS-PREVENTDELEGATION", "TRUE");
        XProperty lotusVersion = new XProperty("X-LOTUS-NOTESVERSION", "2");
        XProperty lotusAppType = new XProperty("X-LOTUS-APPTTYPE", "3");

        propertyList.addAll(Arrays.asList(locationProperty, lotusPreventDelegation,
                microsoftDisallowCounter, lotusVersion, lotusAppType, lotusBroadcast,
                lotusPreventCounter, lotusUtf8, outlookForceOpen, msAttachment));
        return propertyList;
    }

    private List<Attendee> getAttendeeList() throws URISyntaxException {
        List<Attendee> attendeeList = new ArrayList<>();

        Attendee dev1 = new Attendee("gaputinseva@gmail.com");
        dev1.getParameters().add(Rsvp.FALSE);
        dev1.getParameters().add(PartStat.ACCEPTED);
        dev1.getParameters().add(Role.CHAIR);

        Attendee dev2 = new Attendee("gaputin@hotmail.com");
        dev2.getParameters().add(Rsvp.TRUE);
        dev2.getParameters().add(PartStat.ACCEPTED);
        dev2.getParameters().add(Role.REQ_PARTICIPANT);

        Attendee dev3 = new Attendee("UHaputsin@ibagroup.eu");
        dev3.getParameters().add(Rsvp.TRUE);
        dev3.getParameters().add(PartStat.ACCEPTED);
        dev3.getParameters().add(Role.REQ_PARTICIPANT);

        Attendee dev4 = new Attendee("gaputinsevaiba@gmail.com");
        dev4.getParameters().add(Rsvp.FALSE);
        dev4.getParameters().add(PartStat.ACCEPTED);
        dev4.getParameters().add(Role.REQ_PARTICIPANT);

        attendeeList.add(dev1);
        attendeeList.add(dev2);
        attendeeList.add(dev3);
        attendeeList.add(dev4);

        return attendeeList;
    }
}
