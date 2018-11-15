package com.business.ical.service.implementation;

import com.business.ical.service.interfaces.SimpleEvent;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Cn;
import net.fortuna.ical4j.model.parameter.PartStat;
import net.fortuna.ical4j.model.parameter.Role;
import net.fortuna.ical4j.model.parameter.Rsvp;
import net.fortuna.ical4j.model.property.*;
import net.fortuna.ical4j.util.UidGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SimpleEventImpl implements SimpleEvent {
    private static final Logger logger = LoggerFactory.getLogger(SimpleEventImpl.class);
    private static final String UID = "2321674543423";

    @Bean("invite")
    @Override
    public Calendar simpleEventInvitation() throws ParseException, URISyntaxException {
        Calendar simpleEventInvitation = new Calendar();
        simpleEventInvitation.getProperties().add(Version.VERSION_2_0);
        simpleEventInvitation.getProperties().add(CalScale.GREGORIAN);
        simpleEventInvitation.getProperties().add(new ProdId("-//Event Central//EN"));
        simpleEventInvitation.getProperties().add(Method.REQUEST);

        DateTime eventStartDateTime = new DateTime("20181118T130000Z");
        DateTime eventEndDateTime = new DateTime("20181118T152500Z");

        VEvent event = new VEvent(eventStartDateTime, eventEndDateTime, "Invite Summary");
        event.getProperties().add(new Description("Create description"));
        event.getProperties().add(new Sequence("0"));
        event.getProperties().add(Transp.OPAQUE);
//        PropertyList<Property> eventProperties = getEventProperties();
//        event.getProperties().addAll(eventProperties);

//        XProperty lotusNotesType = new XProperty("X-LOTUS-NOTICETYPE", "I");
//        event.getProperties().add(lotusNotesType);

        event.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        event.getProperties().getProperty(Property.ORGANIZER).getParameters().add(new Cn("Haputsin Usevalad"));

        event.getProperties().addAll(getAttendeeList());

        Uid uid = new Uid(UID);
        event.getProperties().add(uid);

        simpleEventInvitation.getComponents().add(event);
        simpleEventInvitation.validate();
        logger.info("\nCalendar: \n" + simpleEventInvitation.toString());
        return simpleEventInvitation;
    }

    @Bean("update")
    @Override
    public Calendar simpleEventUpdate() throws ParseException, URISyntaxException {
        Calendar simpleEventUpdate = new Calendar();
        simpleEventUpdate.getProperties().add(Version.VERSION_2_0);
        simpleEventUpdate.getProperties().add(CalScale.GREGORIAN);
        simpleEventUpdate.getProperties().add(new ProdId("-//Event Central//EN"));
        simpleEventUpdate.getProperties().add(Method.REQUEST);

        DateTime eventStartDateTime = new DateTime("20181118T130000Z");
        DateTime eventEndDateTime = new DateTime("20181118T152500Z");

        VEvent event = new VEvent(eventStartDateTime, eventEndDateTime, "Update summary");
        event.getProperties().add(new Description("Update Description"));
        event.getProperties().add(Transp.OPAQUE);
        PropertyList<Property> eventProperties = getEventProperties();
        event.getProperties().addAll(eventProperties);

        XProperty lotusNotesType = new XProperty("X-LOTUS-NOTICETYPE", "E");
        XProperty subjectUpdate = new XProperty("X-LOTUS-UPDATE-SUBJECT", "New Subject For Lotus");
        XProperty sequenceUpdate = new XProperty("X-LOTUS-UPDATE-SEQ", "1");
        event.getProperties().add(subjectUpdate);
        event.getProperties().add(sequenceUpdate);
        event.getProperties().add(lotusNotesType);

        event.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        event.getProperties().getProperty(Property.ORGANIZER).getParameters().add(new Cn("Haputsin Usevalad"));

        event.getProperties().addAll(getAttendeeList());

        Uid uid = new Uid(UID);
        event.getProperties().add(uid);

        simpleEventUpdate.getComponents().add(event);
        simpleEventUpdate.validate();
        logger.info("\nCalendar: \n" + simpleEventUpdate.toString());
        return simpleEventUpdate;
    }

    @Bean("reschedule")
    @Override
    public Calendar simpleEventReschedule() throws ParseException, URISyntaxException {
        Calendar simpleEventReschedule = new Calendar();
        simpleEventReschedule.getProperties().add(Version.VERSION_2_0);
        simpleEventReschedule.getProperties().add(CalScale.GREGORIAN);
        simpleEventReschedule.getProperties().add(new ProdId("-//Event Central//EN"));
        simpleEventReschedule.getProperties().add(Method.REQUEST);

        DateTime eventStartDateTime = new DateTime("20181120T11000Z");
        DateTime eventEndDateTime = new DateTime("20181120T132500Z");

        VEvent event = new VEvent(eventStartDateTime, eventEndDateTime, "Reschedule summary");
        event.getProperties().add(new Description("Reschedule description"));

//        PropertyList<Property> eventProperties = getEventProperties();
//        event.getProperties().addAll(eventProperties);
        event.getProperties().add(Transp.OPAQUE);

//        XProperty lotusNotesType = new XProperty("X-LOTUS-NOTICETYPE", "U");
//        event.getProperties().add(lotusNotesType);
        event.getProperties().add(new Sequence("2"));

        event.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        event.getProperties().getProperty(Property.ORGANIZER).getParameters().add(new Cn("Haputsin Usevalad"));
//        XProperty sequenceUpdate = new XProperty("X-LOTUS-UPDATE-SEQ", "2");
//        event.getProperties().add(sequenceUpdate);

        event.getProperties().addAll(getAttendeeList());

        Uid uid = new Uid(UID);
        event.getProperties().add(uid);

        simpleEventReschedule.getComponents().add(event);
        simpleEventReschedule.validate();
        logger.info("\nCalendar: \n" + simpleEventReschedule.toString());
        return simpleEventReschedule;
    }

    @Bean("cancel")
    @Override
    public Calendar simpleEventCancel() throws ParseException, URISyntaxException {
        Calendar calendar = new Calendar();
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);
        calendar.getProperties().add(new ProdId("-//Event Central//EN"));
        calendar.getProperties().add(Method.CANCEL);

        DateTime eventStartDateTime = new DateTime("20181120T11000Z");
        DateTime eventEndDateTime = new DateTime("20181120T132500Z");

        VEvent event = new VEvent(eventStartDateTime, eventEndDateTime,  "Canceled summary");
        event.getProperties().add(new Description("Cancel description"));
        event.getProperties().add(Transp.OPAQUE);

//        PropertyList<Property> eventProperties = getEventProperties();
//        event.getProperties().addAll(eventProperties);
//        XProperty lotusNotesType = new XProperty("X-LOTUS-NOTICETYPE", "S");
//        event.getProperties().add(lotusNotesType);
        event.getProperties().add(new Sequence("3"));

        event.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        event.getProperties().getProperty(Property.ORGANIZER).getParameters().add(new Cn("Haputsin Usevalad"));

        event.getProperties().addAll(getAttendeeList());

        event.getProperties().add(Clazz.PUBLIC);
        event.getProperties().add(Status.VEVENT_CANCELLED);

        Uid uid = new Uid(UID);
        event.getProperties().add(uid);

        calendar.getComponents().add(event);
        calendar.validate();
        logger.info("\nCalendar: \n" + calendar.toString());
        return calendar;
    }

    private PropertyList<Property> getEventProperties () {
        PropertyList<Property> propertyList = new PropertyList<>();

        String location = "Conference room A103";
        Location locationProperty = new Location(location);

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

//        Attendee dev1 = new Attendee("gaputinseva@gmail.com");
//        dev1.getParameters().add(Rsvp.FALSE);
//        dev1.getParameters().add(PartStat.ACCEPTED);
//        dev1.getParameters().add(Role.CHAIR);
//
//        Attendee dev2 = new Attendee("gaputin@hotmail.com");
//        dev2.getParameters().add(Rsvp.TRUE);
//        dev2.getParameters().add(PartStat.NEEDS_ACTION);
//        dev2.getParameters().add(Role.REQ_PARTICIPANT);
//
//        Attendee dev3 = new Attendee("UHaputsin@ibagroup.eu");
//        dev3.getParameters().add(Rsvp.TRUE);
//        dev3.getParameters().add(PartStat.NEEDS_ACTION);
//        dev3.getParameters().add(Role.REQ_PARTICIPANT);

        Attendee dev4 = new Attendee("gaputinsevaiba@gmail.com");
        dev4.getParameters().add(Rsvp.FALSE);
        dev4.getParameters().add(PartStat.NEEDS_ACTION);
        dev4.getParameters().add(Role.REQ_PARTICIPANT);

//        attendeeList.add(dev1);
//        attendeeList.add(dev2);
//        attendeeList.add(dev3);
        attendeeList.add(dev4);

        return attendeeList;
    }
}
