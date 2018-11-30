package com.business.mail.controller;

import com.business.ical.service.interfaces.ComplexUpdates;
import com.business.mail.model.EmailRequest;
import com.business.mail.model.EmailResponse;
import com.business.mail.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/send/event/complex1", produces = "application/json", consumes = "application/json")
public class ComplexUpdatesController {
    private EventService eventService;
    private ComplexUpdates complexUpdates;

    @Autowired
    public ComplexUpdatesController(EventService eventService, ComplexUpdates complexUpdates) {
        this.eventService = eventService;
        this.complexUpdates = complexUpdates;
    }

    @PostMapping(value = "/invite")
    public EmailResponse sendComplexInvite(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventPublish(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), complexUpdates.invitation());
    }

    @PostMapping(value = "/resch")
    public EmailResponse sendComplexReschedule(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventPublish(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), complexUpdates.firstReschedule());
    }

    @PostMapping(value = "/second")
    public EmailResponse sendComplexRecur(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventPublish(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), complexUpdates.secondReschedule());
    }

    @PostMapping(value = "/recurinvite")
    public EmailResponse sendComplexRecurInvite(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventPublish(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), complexUpdates.firstInvite());
    }
}
