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

import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CreateCalendarImpl implements CreateCalendar {
    private static final Logger logger = LoggerFactory.getLogger(CreateCalendarImpl.class);

    @Bean
    @Override
    public Calendar calendar() throws ParseException, URISyntaxException {
        DateTime eventStartDateTime1 = new DateTime("20181029T130000Z");
        DateTime eventEndDateTime1 = new DateTime("20181029T142500Z");

        DateTime eventStartDateTime2 = new DateTime("20181030T145000Z");
        DateTime eventEndDateTime2 = new DateTime("20181030T154500Z");

        DateTime eventStartDateTime3 = new DateTime("20181031T111500Z");
        DateTime eventEndDateTime3 = new DateTime("20181031T140000Z");

        Calendar calendar = new Calendar();
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);
        calendar.getProperties().add(new ProdId("-//Event Central//EN"));
//        calendar.getProperties().add(Method.REQUEST);

        VEvent event1 = new VEvent(
                eventStartDateTime1,
                eventEndDateTime1,
                "Test IBM Subject");
        PropertyList<Property> eventProperties1 = getEventProperties(event1);
        Uid uid1 = new Uid("1111111111111111111");
        event1.getProperties().add(uid1);
        event1.getProperties().addAll(eventProperties1);

        VEvent event2 = new VEvent(
                eventStartDateTime2,
                eventEndDateTime2,
                "Test IBM Subject");
        PropertyList<Property> eventProperties2 = getEventProperties(event2);
        Uid uid2 = new Uid("22222222222222222222");
        event2.getProperties().add(uid2);
        event2.getProperties().addAll(eventProperties2);

        VEvent event3 = new VEvent(
                eventStartDateTime3,
                eventEndDateTime3,
                "Test IBM Subject");
        PropertyList<Property> eventProperties3 = getEventProperties(event3);
        Uid uid3 = new Uid("33333333333333333333");
        event3.getProperties().add(uid3);
        event3.getProperties().addAll(eventProperties3);

        List<VEvent> list = new ArrayList<>();
        list.add(event1);
        list.add(event2);
        list.add(event3);

        calendar.getComponents().addAll(list);

        logger.info("\nCalendar:\n" + calendar);
        return calendar;
    }

    private PropertyList<Property> getEventProperties(VEvent event) throws URISyntaxException {

        PropertyList<Property> propertyList = new PropertyList<>();

        String organizer = "gaputinseva@gmail.com";
        String location = "Conference room A103";

        Organizer organizerProperty = new Organizer(organizer);
        organizerProperty.getParameters().add(new Cn("Event Central 3"));
        Location locationProperty = new Location(location);

        Description descriptionProperty = new Description("Description");
        Transp transpProperty = Transp.TRANSPARENT;
//        XProperty lotusBroadcast = new XProperty("X-LOTUS-BROADCAST","TRUE");
//        XProperty lotusNotesType = new XProperty("X-LOTUS-NOTICETYPE", "A");
//        XProperty lotusPreventCounter = new XProperty("X-LOTUS-PREVENTCOUNTER", "FALSE");
//        XProperty microsoftDisallowCounter = new XProperty("X-MICROSOFT-DISALLOW-COUNTER", "TRUE");
//        XProperty lotusPreventDelegation = new XProperty("X-LOTUS-PREVENTDELEGATION", "TRUE");
//        XProperty lotusVersion = new XProperty("X-LOTUS-NOTESVERSION","2");
//        XProperty lotusAppType = new XProperty("X-LOTUS-APPTTYPE", "3");
//        XProperty updateSeq = new XProperty("X-LOTUS-UPDATE-SEQ");
//        XProperty saf = new XProperty("X-")
//        CustomXProperty lotusUpdateWISL = new CustomXProperty("X-LOTUS-UPDATE-WISL");
        //lotusUpdateWISL.setValue("$S:" + updateVersion + ";$L:" + updateVersion + ";$B:" + updateVersion);

        propertyList.addAll(Arrays.asList(transpProperty, organizerProperty, descriptionProperty, locationProperty/*
                lotusPreventDelegation, microsoftDisallowCounter, lotusVersion, lotusAppType,
                 lotusBroadcast, lotusNotesType, lotusPreventCounter,
                updateSeq*/));

        return propertyList;
    }


}
