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

import javax.validation.constraints.Email;

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

    @PostMapping(value = "/inviteAll")
    public EmailResponse sendComplexInvite(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventPublish(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), complexUpdates.invitationAll());
    }

    @PostMapping(value = "/rescheduleFirst")
    public EmailResponse sendComplexFirstReschedule(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventPublish(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), complexUpdates.firstRescheduleAll());
    }

    @PostMapping(value = "/rescheduleAll")
    public EmailResponse sendComplexSecondReschedule(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventPublish(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), complexUpdates.secondRescheduleAll());
    }

    @PostMapping(value = "/inviteOutlook")
    public EmailResponse sendComplexRecurInvite(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventPublish(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), complexUpdates.inviteOutlook());
    }

    @PostMapping(value = "/rescheduleFirstOutlook")
    public EmailResponse sendComplexFirstOutlookReschedule(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventPublish(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), complexUpdates.firstRescheduleOutlook());
    }

    @PostMapping(value = "/rescheduleSecondOutlook")
    public EmailResponse sendComplexSecondOutlookReschedule(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventPublish(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), complexUpdates.secondRescheduleOutlook());
    }
}
