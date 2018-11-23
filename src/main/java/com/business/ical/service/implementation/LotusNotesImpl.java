package com.business.ical.service.implementation;

import com.business.ical.service.interfaces.LotusNotes;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.parameter.*;
import net.fortuna.ical4j.model.property.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class LotusNotesImpl implements LotusNotes {
    private static final Logger logger = LoggerFactory.getLogger(EventWithHtmlImpl.class);
    private Uid UID;
    private final String HTML_STRING = "<html>\n" +
            "   <head>\n" +
            "      <title>Simple One Column Layout</title>\n" +
            "      <meta charset=\"utf-8\" />\n" +
            "      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"/>\n" +
            "      <style>html, body, div, p, ul, ol, li, h1, h2, h3, h4, h5, h6 {margin: 0;padding: 0;}body {font-size:10px;line-height:10px;}@media all and (max-width: 599px) {.container600 {width: 100%;}}</style>\n" +
            "   </head>\n" +
            "   <body style=\"background-color:#F4F4F4;\">\n" +
            "      <div style=\"background-color:#F4F4F4;\"><div style=\"width:600px;margin:0 auto;\"><div style=\"padding: 10px;\"><div style=\"padding:30px; background-color:#FFFFFF;color:#58585A;\"><img alt=\"\" src=\"https://edmdesigner.github.io/modern-html-email-tutorial/lesson03/step-00/img/logo.png\" style=\"display:block;width:210px\"/></div>\n" +
            "      <div style=\"padding:30px; background-color:#F8F7F0;color:#58585A;\">\n" +
            "      <h1 style=\"font-size:28px;line-height:32px;margin-bottom:24px;\">Confirm Registration</h1>\n" +
            "      <p style=\"font-size:16px;line-height:20px;font-family: Georgia, Arial, sans-serif;\">But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness.</p><a href=\"http://edmdesigner.com\" style=\"display:block;color:#58585A;font-size:16px;line-height:20px;font-family:Georgia,Arial,sans-serif; text-align:center;padding-top:30px;padding-bottom:30px;\">Confirm</a></div><div style=\"padding:30px; background-color:#58585A;color:#FFFFFF;\"><p style=\"font-size:16px;line-height:20px;font-family: Georgia, Arial, sans-serif; text-align: center;\">2017 @ COPYRIGHT - EDMDESIGNER</p></div></div></div></div>\n" +
            "   </body>\n" +
            "</html>";

    public String createLotusNotesCalendar() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BEGIN:VCALENDAR\n");
        stringBuilder.append("VERSION:2.0\n");
        stringBuilder.append("PRODID:-//Microsoft Corporation//EN\n");
        stringBuilder.append("CALSCALE:GREGORIAN\n");
        stringBuilder.append("METHOD:REQUEST\n");
        stringBuilder.append("BEGIN:VEVENT\n");
        stringBuilder.append("ATTENDEE;CN=UHaputsin@ibagroup.eu;RSVP=TRUE:mailto:UHaputsin@ibagroup.eu\n");
        stringBuilder.append("DESCRIPTION:Project XYZ\n");
        stringBuilder.append("DTSTAMP:20181122T120000Z\n");
        stringBuilder.append("DTSTART:20181123T132000Z\n");
        stringBuilder.append("DTEND:20181123T152000Z\n");
        stringBuilder.append("ORGANIZER;CN=\"Usevalad Haputsin\":mailto:gaputinsevaiba@gmail.com\n");
        stringBuilder.append("SEQUENCE:0\n");
        stringBuilder.append("SUMMARY:Some summary\n");
        stringBuilder.append("TRANSP:OPAQUE\n");
        stringBuilder.append("UID:3421421442\n");
        stringBuilder.append("X-ALT-DESC;FMTTYPE=text/html:\t<html> <head> <title>Playing with Inline Styles</title> </head> <body> <p style=\"color:blue;font-size:46px;\"> I'm a big, blue, <strong>strong</strong> paragraph </p> </body></html>\n");
        stringBuilder.append("END:VEVENT\n");
        stringBuilder.append("END:VCALENDAR\n");

        String icsString = stringBuilder.toString();

        return icsString;
    }

    private PropertyList<Property> getEventProperties() {
        PropertyList<Property> propertyList = new PropertyList<>();

        Location locationProperty = new Location("Conference room A103");

        XProperty altDesc = new XProperty("X-ALT-DESC");
        altDesc.getParameters().add(new FmtType("text/html"));
        altDesc.setValue(HTML_STRING);
        XProperty lotusBroadcast = new XProperty("X-LOTUS-BROADCAST", "TRUE");
        XProperty lotusPreventCounter = new XProperty("X-LOTUS-PREVENTCOUNTER", "FALSE");
        XProperty microsoftDisallowCounter = new XProperty("X-MICROSOFT-DISALLOW-COUNTER", "TRUE");
        XProperty outlookForceOpen = new XProperty("X-MS-OLK-FORCEINSPECTOROPEN", "TRUE");
        XProperty msAttachment = new XProperty("X-MS-ATTACHMENT", "YES");
        XProperty lotusUtf8 = new XProperty("X-LOTUS-CHARSET", "UTF-8");
        XProperty lotusPreventDelegation = new XProperty("X-LOTUS-PREVENTDELEGATION", "TRUE");
        XProperty lotusVersion = new XProperty("X-LOTUS-NOTESVERSION", "2");
        XProperty lotusAppType = new XProperty("X-LOTUS-APPTTYPE", "3");
        XProperty updateLotus = new XProperty("X-LOTUS-UPDATE-SEQ",  "1");

        propertyList.addAll(Arrays.asList(/*altDesc,*/locationProperty, lotusPreventDelegation,
                microsoftDisallowCounter, lotusVersion, lotusAppType, lotusBroadcast,
                lotusPreventCounter, lotusUtf8, outlookForceOpen, msAttachment, updateLotus));
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

