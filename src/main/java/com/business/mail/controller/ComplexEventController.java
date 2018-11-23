package com.business.mail.controller;

import com.business.ical.service.interfaces.ComplexEvents;
import com.business.mail.model.EmailRequest;
import com.business.mail.model.EmailResponse;
import com.business.mail.service.EventService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/send/event/complex", produces = "application/json", consumes = "application/json")
public class ComplexEventController {
    private EventService eventService;
    private ComplexEvents calendar;

    @Autowired
    public ComplexEventController(EventService eventService, ComplexEvents calendar) {
        this.eventService = eventService;
        this.calendar = calendar;
    }

    @ApiOperation(value = "Send multi event invites via mail message")
    @PostMapping(value = "/invite")
    public EmailResponse sendComplexInvite(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventPublish(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), calendar.complexEventInvite());
    }

    @ApiOperation(value = "Send multi event update via mail message")
    @PostMapping(value = "/update")
    public EmailResponse sendComplexUpdate(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventPublish(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), calendar.complexEventUpdateAtLeastTwo());
    }

    @ApiOperation(value = "Send multi event reschedule via mail message")
    @PostMapping(value = "/reschedule")
    public EmailResponse sendComplexReschedule(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventPublish(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), calendar.complexEventRescheduleAtLeastTwo());
    }

    @ApiOperation(value = "Send multi event cancel via mail message")
    @PostMapping(value = "/cancel")
    public EmailResponse sendComplexCancel(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventPublish(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), calendar.complexEventCancelAtLeastTwo());
    }
}
