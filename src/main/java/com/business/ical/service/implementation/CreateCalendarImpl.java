package com.business.ical.service.implementation;

import com.business.ical.service.interfaces.CreateCalendar;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

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
        calendar.getProperties().add(Method.PUBLISH);

        //=========================================================================================
        DateTime eventStartDateTime1 = new DateTime("20181112T130000Z");
        DateTime eventEndDateTime1 = new DateTime("20181112T152500Z");

        VEvent event1 = new VEvent(eventStartDateTime1, eventEndDateTime1, "Test IBM Subject Num 1");
//        VEvent event1 = new VEvent(true);

        PropertyList<Property> eventProperties1 = getEventProperties();

        event1.getProperties().add(Transp.OPAQUE);
        event1.getProperties().addAll(eventProperties1);
        event1.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        event1.getProperties().add(new Sequence("0"));

        Uid uid1 = new Uid("421421421");
        event1.getProperties().add(uid1);
        //*****************************************************************************************
        DateTime eventStartDateTime2 = new DateTime("20181113T120000Z");
        DateTime eventEndDateTime2 = new DateTime("20181113T142500Z");

        VEvent event2 = new VEvent(eventStartDateTime2, eventEndDateTime2, "Test IBM 2");
//        VEvent event1 = new VEvent(true);

        PropertyList<Property> eventProperties2 = getEventProperties();

        event2.getProperties().add(Transp.OPAQUE);
        event2.getProperties().addAll(eventProperties2);
        event2.getProperties().add(new Organizer("mailto:gaputinseva@gmail.com"));
        event2.getProperties().add(new Sequence("0"));

        Uid uid2 = new Uid("541421421");
        event2.getProperties().add(uid2);

        calendar.getComponents().add(event1);
        calendar.getComponents().add(event2);
        logger.info("\nCalendar: \n" + calendar.toString());
        return calendar;
    }

    private PropertyList<Property> getEventProperties() {

        PropertyList<Property> propertyList = new PropertyList<>();

        String location = "Conference room A103";
        Location locationProperty = new Location(location);

        Description descriptionProperty = new Description("Description");
        XProperty lotusBroadcast = new XProperty("X-LOTUS-BROADCAST","TRUE");
        XProperty lotusNotesType = new XProperty("X-LOTUS-NOTICETYPE", "I");
        XProperty lotusPreventCounter = new XProperty("X-LOTUS-PREVENTCOUNTER", "FALSE");
        XProperty microsoftDisallowCounter = new XProperty("X-MICROSOFT-DISALLOW-COUNTER", "TRUE");
        XProperty outlookForceOpen = new XProperty("X-MS-OLK-FORCEINSPECTOROPEN", "TRUE");
        XProperty msAttachment = new XProperty("X-MS-ATTACHMENT", "YES");
        XProperty lotusUtf8 = new XProperty("X-LOTUS-CHARSET","UTF-8");
        XProperty lotusPreventDelegation = new XProperty("X-LOTUS-PREVENTDELEGATION", "TRUE");
        XProperty lotusVersion = new XProperty("X-LOTUS-NOTESVERSION","2");
        XProperty lotusAppType = new XProperty("X-LOTUS-APPTTYPE", "3");

        propertyList.addAll(Arrays.asList(descriptionProperty, locationProperty, lotusPreventDelegation,
                microsoftDisallowCounter, lotusVersion, lotusAppType, lotusBroadcast, lotusNotesType,
                lotusPreventCounter, lotusUtf8, outlookForceOpen, msAttachment));

        return propertyList;
    }


}
