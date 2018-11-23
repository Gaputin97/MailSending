package com.business.mail.controller;

import com.business.ical.service.interfaces.EventWithHtml;
import com.business.mail.model.EmailRequest;
import com.business.mail.model.EmailResponse;
import com.business.mail.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send/event/html")
public class EventWithHtmlController {
    private EventService eventService;
    private EventWithHtml eventWithHtml;

    @Autowired
    public EventWithHtmlController(EventService eventService, EventWithHtml eventWithHtml) {
        this.eventService = eventService;
        this.eventWithHtml = eventWithHtml;
    }

    @PostMapping("/alt-rep")
    public EmailResponse sendHtmlWithAltRep(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventRequestWithHtml(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), eventWithHtml.createEventWithAltRep());
    }

//    @PostMapping("/alt-desc")
//    public EmailResponse sendHtmlWithAltDesc(@RequestBody EmailRequest emailRequest) {
//        return eventService.sendEventRequest(emailRequest.getRecipientEmail(),
//                emailRequest.getMessageSubject(), eventWithHtml.createEventWithAtlDesc());
//    }
}
