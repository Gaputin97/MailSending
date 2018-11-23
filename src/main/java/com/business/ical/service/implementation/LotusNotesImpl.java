package com.business.ical.service.implementation;

import com.business.ical.service.interfaces.LotusNotes;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.*;
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
public class LotusNotesImpl implements LotusNotes {
    private static final Logger logger = LoggerFactory.getLogger(EventWithHtmlImpl.class);
    private Uid UID;

    FixedUidGenerator fixedUidGenerator;


    @Override
    public Calendar createLotusNotesCalendar() {
        try {
            fixedUidGenerator = new FixedUidGenerator("UHaputsin");
        } catch (SocketException e) {
            e.printStackTrace();
        }
        net.fortuna.ical4j.model.Calendar complexEventCancel = new net.fortuna.ical4j.model.Calendar();
        complexEventCancel.getProperties().add(Version.VERSION_2_0);
        complexEventCancel.getProperties().add(CalScale.GREGORIAN);
        complexEventCancel.getProperties().add(new ProdId("-//Event Central//EN"));
        complexEventCancel.getProperties().add(Method.REQUEST);
        //***************************************************************************************************
        DateTime eventStartDateTime1 = null;
        DateTime eventEndDateTime1 = null;
        try {
            eventStartDateTime1 = new DateTime("20181125T190000Z");
            eventEndDateTime1 = new DateTime("20181125T203700Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event1 = new VEvent(eventStartDateTime1, eventEndDateTime1, "First Shadow VEvent");
        event1.getProperties().add(Transp.OPAQUE);
        try {
            event1.getProperties().addAll(getAttendeeList());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ParameterList list = new ParameterList();
        PeriodList periods = new PeriodList();
        try {
            periods.add(new Period(new DateTime("20181127T150000Z"), new DateTime("20181127T172100Z")));
            periods.add(new Period(new DateTime("20181130T083000Z"), new DateTime("20181130T120500Z")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        list.add(Value.PERIOD);
        RDate rDate = new RDate(list, periods);
        event1.getProperties().add(rDate);

        event1.getProperties().add(new Sequence("0"));
        event1.getProperties().add(new Description("Invite description for the first event"));
        event1.getProperties().add(new Location("London"));
        UID = fixedUidGenerator.generateUid();
        event1.getProperties().add(UID);
        try {
            event1.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
//        PropertyList<Property> eventProperties1 = getEventProperties();
//        event1.getProperties().addAll(eventProperties1);
//
//        XProperty lotusNotesType1 = new XProperty("X-LOTUS-NOTICETYPE", "I");
//        event1.getProperties().add(lotusNotesType1);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //***************************************************************************************************
        DateTime eventStartDateTime2 = null;
        DateTime eventEndDateTime2 = null;
        try {
            eventStartDateTime2 = new DateTime("20181127T150000Z");
            eventEndDateTime2 = new DateTime("20181127T172100Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event2 = new VEvent(eventStartDateTime2, eventEndDateTime2, "Invite Summary - 1");
        event2.getProperties().add(Transp.OPAQUE);
        try {
            event2.getProperties().addAll(getAttendeeList());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        event2.getProperties().add(new Sequence("1"));
        event2.getProperties().add(new Description("Invite decs 1"));
        event2.getProperties().add(UID);
        try {
            event2.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
//        PropertyList<Property> eventProperties2 = getEventProperties();
//        event2.getProperties().addAll(eventProperties2);

//        XProperty updateLotus2 = new XProperty("X-LOTUS-UPDATE-SEQ",  "1");
//        event2.getProperties().add(updateLotus2);
//        XProperty lotusNotesType2 = new XProperty("X-LOTUS-NOTICETYPE", "U");
//        event2.getProperties().add(lotusNotesType2);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //***************************************************************************************************
        DateTime eventStartDateTime3 = null;
        DateTime eventEndDateTime3 = null;
        try {
            eventStartDateTime3 = new DateTime("20181130T083000Z");
            eventEndDateTime3 = new DateTime("20181130T120500Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VEvent event3 = new VEvent(eventStartDateTime3, eventEndDateTime3, "Invite Summary - 2");
        event3.getProperties().add(Transp.OPAQUE);
        try {
            event3.getProperties().addAll(getAttendeeList());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        event3.getProperties().add(new Sequence("1"));
        event3.getProperties().add(new Description("Invite decs 1"));
        event3.getProperties().add(UID);
        try {
            event3.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
//        PropertyList<Property> eventProperties3 = getEventProperties();
//        event3.getProperties().addAll(eventProperties3);

//        XProperty updateLotus3 = new XProperty("X-LOTUS-UPDATE-SEQ",  "2");
//        event3.getProperties().add(updateLotus3);
//        XProperty lotusNotesType3 = new XProperty("X-LOTUS-NOTICETYPE", "U");
//        event3.getProperties().add(lotusNotesType3);
        //***************************************************************************************************
        complexEventCancel.getComponents().add(event1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        complexEventCancel.getComponents().add(event2);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        complexEventCancel.getComponents().add(event3);

        complexEventCancel.validate();
        logger.info("\nCalendar: \n" + complexEventCancel.toString());

        return complexEventCancel;
    }

    private PropertyList<Property> getEventProperties() {
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

