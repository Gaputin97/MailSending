package com.business.mail.controller;

import com.business.mail.model.EmailRequest;
import com.business.mail.model.EmailResponse;
import com.business.mail.service.EventService;
import io.swagger.annotations.ApiOperation;
import net.fortuna.ical4j.model.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/send/event/simple", produces = "application/json", consumes = "application/json")
public class SimpleEventController {
    private EventService eventService;

    private Calendar simpleEventInvite;

    private Calendar simpleEventUpdate;

    private Calendar simpleEventReschedule;

    private Calendar simpleEventCancel;

    @Autowired
    public SimpleEventController(EventService eventService,
                                 @Qualifier("invite") Calendar simpleEventInvite,
                                 @Qualifier("update") Calendar simpleEventUpdate,
                                 @Qualifier("reschedule") Calendar simpleEventReschedule,
                                 @Qualifier("cancel") Calendar simpleEventCancel) {
        this.eventService = eventService;
        this.simpleEventInvite = simpleEventInvite;
        this.simpleEventUpdate = simpleEventUpdate;
        this.simpleEventReschedule = simpleEventReschedule;
        this.simpleEventCancel = simpleEventCancel;
    }

    @ApiOperation(value = "Send event invite via mail message")
    @PostMapping(value = "/invite")
    public EmailResponse sendSimpleEventInvite(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventRequest(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), simpleEventInvite);
    }

    @ApiOperation(value = "Update event update via mail message")
    @PostMapping(value = "/update")
    public EmailResponse sendSimpleEventUpdate(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventRequest(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), simpleEventUpdate);
    }

    @ApiOperation(value = "Reschedule event reschedule via mail message")
    @PostMapping(value = "/reschedule")
    public EmailResponse sendSimpleEventReschedule(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventRequest(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), simpleEventReschedule);
    }

    @ApiOperation(value = "Cancel event cancel via mail message")
    @PostMapping(value = "/cancel")
    public EmailResponse sendSimpleEventCancel(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventCancel(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), simpleEventCancel);
    }
}
