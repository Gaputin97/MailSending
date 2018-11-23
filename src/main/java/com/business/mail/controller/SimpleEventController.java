package com.business.mail.controller;

import com.business.ical.service.interfaces.SimpleEvent;
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
@RequestMapping(value = "/send/event/simple", produces = "application/json", consumes = "application/json")
public class SimpleEventController {
    private EventService eventService;
    private SimpleEvent simpleEvent;


    @Autowired
    public SimpleEventController(EventService eventService, SimpleEvent calendar) {
        this.eventService = eventService;
        this.simpleEvent = calendar;
    }

    @ApiOperation(value = "Send simple event via mail message")
    @PostMapping(value = "/invite")
    public EmailResponse sendSimpleEventInvite(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventRequest(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), simpleEvent.simpleEventInvitation());
    }

    @ApiOperation(value = "Update simple event via mail message")
    @PostMapping(value = "/update")
    public EmailResponse sendSimpleEventUpdate(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventRequest(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), simpleEvent.simpleEventUpdate());
    }

    @ApiOperation(value = "Reschedule simple event via mail message")
    @PostMapping(value = "/reschedule")
    public EmailResponse sendSimpleEventReschedule(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventRequest(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), simpleEvent.simpleEventReschedule());
    }

    @ApiOperation(value = "Cancel simple event via mail message")
    @PostMapping(value = "/cancel")
    public EmailResponse sendSimpleEventCancel(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventCancel(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), simpleEvent.simpleEventCancel());
    }
}
