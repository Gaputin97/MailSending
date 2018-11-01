package com.business.ical.service.implementation;

import com.business.ical.service.interfaces.CreateCalendar;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.*;
import net.fortuna.ical4j.model.property.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Arrays;

@Service
public class CreateCalendarImpl implements CreateCalendar {
    private static final Logger logger = LoggerFactory.getLogger(CreateCalendarImpl.class);

    @Bean
    @Override
    public Calendar calendar() throws ParseException, URISyntaxException {
        Calendar calendar = new Calendar();
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);
        calendar.getProperties().add(new ProdId("-//Event Central//EN"));
        calendar.getProperties().add(Method.REQUEST);

        DateTime eventStartDateTime = new DateTime("20181031T130000Z");
        DateTime eventEndDateTime = new DateTime("20181031T152500Z");
        VEvent event = new VEvent(eventStartDateTime, eventEndDateTime,"Test IBM Subject");
        PropertyList<Property> eventProperties1 = getEventProperties();

        RRule rule = new RRule("FREQ=WEEKLY;BYDAY=MO,WE,FR;UNTIL=20181115T000000Z");
        event.getProperties().add(rule);
        event.getProperties().add(Transp.OPAQUE);
        event.getProperties().addAll(eventProperties1);

        Attendee dev1 = new Attendee(URI.create("mailto:gaputin@hotmail.com"));
        dev1.getParameters().add(Role.REQ_PARTICIPANT);
        dev1.getParameters().add(Rsvp.FALSE);
        dev1.getParameters().add(new Cn("Developer 1"));

        Attendee dev2 = new Attendee(URI.create("mailto:gaputinsevaiba@gmail.com"));
        dev2.getParameters().add(Role.REQ_PARTICIPANT);
        dev2.getParameters().add(Rsvp.FALSE);
        dev2.getParameters().add(new Cn("Developer 2"));

        Attendee dev3 = new Attendee(URI.create("mailto:UHaputsin@ibagroup.eu"));
        dev3.getParameters().add(Role.REQ_PARTICIPANT);
        dev3.getParameters().add(Rsvp.FALSE);
        dev3.getParameters().add(new Cn("Developer 3"));

        event.getProperties().add(dev1);
        event.getProperties().add(dev2);
        event.getProperties().add(dev3);

        Uid uid1 = new Uid("155446456846810116645610");
        event.getProperties().add(uid1);
        calendar.getComponents().add(event);
        logger.info("\nCalendar:\n" + calendar);

        return calendar;
    }

    private PropertyList<Property> getEventProperties() throws URISyntaxException {

        PropertyList<Property> propertyList = new PropertyList<>();

        String organizer = "gaputinseva@gmail.com";
        String location = "Conference room A103";

        Organizer organizerProperty = new Organizer(organizer);
        organizerProperty.getParameters().add(new Cn("Event Central 3"));
        Location locationProperty = new Location(location);

        Description descriptionProperty = new Description("Description");
        Transp transpProperty = Transp.TRANSPARENT;
        XProperty lotusBroadcast = new XProperty("X-LOTUS-BROADCAST","TRUE");
        XProperty lotusNotesType = new XProperty("X-LOTUS-NOTICETYPE", "A");
        XProperty lotusPreventCounter = new XProperty("X-LOTUS-PREVENTCOUNTER", "FALSE");
        XProperty microsoftDisallowCounter = new XProperty("X-MICROSOFT-DISALLOW-COUNTER", "TRUE");
        XProperty outlookForceOpen = new XProperty("X-MS-OLK-FORCEINSPECTOROPEN", "TRUE");
        XProperty msAttachment = new XProperty("X-MS-ATTACHMENT", "YES");
        XProperty lotusUtf8 = new XProperty("X-LOTUS-CHARSET","UTF-8");
        XProperty lotusPreventDelegation = new XProperty("X-LOTUS-PREVENTDELEGATION", "TRUE");
        XProperty lotusVersion = new XProperty("X-LOTUS-NOTESVERSION","2");
        XProperty lotusAppType = new XProperty("X-LOTUS-APPTTYPE", "3");

        propertyList.addAll(Arrays.asList(transpProperty, organizerProperty, descriptionProperty, locationProperty,
                lotusPreventDelegation, microsoftDisallowCounter, lotusVersion, lotusAppType,
                 lotusBroadcast, lotusNotesType, lotusPreventCounter, lotusUtf8, outlookForceOpen, msAttachment));

        return propertyList;
    }


}
