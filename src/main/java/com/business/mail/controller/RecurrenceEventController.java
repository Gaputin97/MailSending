package com.business.mail.controller;

import com.business.ical.service.interfaces.RecurrenceEvent;
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
@RequestMapping(value = "/send/event/recurrence", produces = "application/json", consumes = "application/json")
public class RecurrenceEventController {
    private EventService eventService;
    private RecurrenceEvent recurrenceEvent;


    @Autowired
    public RecurrenceEventController(EventService eventService, RecurrenceEvent calendar) {
        this.eventService = eventService;
        this.recurrenceEvent = calendar;
    }

    @ApiOperation(value = "Send recurrence event invite via mail message")
    @PostMapping(value = "/invite")
    public EmailResponse sendRecurrenceInvite(@RequestBody EmailRequest emailRequest) {
        return eventService.sendHtml(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), recurrenceEvent.recurInvitation());
    }

    @ApiOperation(value = "Send recurrence event update via mail message")
    @PostMapping(value = "/update")
    public EmailResponse sendRecurrenceUpdate(@RequestBody EmailRequest emailRequest) {
        return eventService.sendHtml(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), recurrenceEvent.recurUpdate());
    }

    @ApiOperation(value = "Send recurrence event update via mail message")
    @PostMapping(value = "/update/single")
    public EmailResponse sendRecurrenceSingleUpdate(@RequestBody EmailRequest emailRequest) {
        return eventService.sendHtml(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), recurrenceEvent.recurSingleUpdate());
    }

    @ApiOperation(value = "Send recurrence update for one or more events via mail message")
    @PostMapping(value = "/update/more")
    public EmailResponse sendRecurrenceMoreThanOneUpdate(@RequestBody EmailRequest emailRequest) {
        return eventService.sendHtml(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), recurrenceEvent.recurOnePlusUpdate());
    }

    @ApiOperation(value = "Send recurrence event reschedule via mail message")
    @PostMapping(value = "/reschedule")
    public EmailResponse sendRecurrenceReschedule(@RequestBody EmailRequest emailRequest) {
        return eventService.sendHtml(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), recurrenceEvent.recurReschedule());
    }

    @ApiOperation(value = "Send recurrence event reschedule via mail message")
    @PostMapping(value = "/reschedule/single")
    public EmailResponse sendRecurrenceSingleReschedule(@RequestBody EmailRequest emailRequest) {
        return eventService.sendHtml(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), recurrenceEvent.recurSingleReschedule());
    }

    @ApiOperation(value = "Send recurrence single event reschedule via mail message")
    @PostMapping(value = "/reschedule/more")
    public EmailResponse sendRecurrenceMoreThanOneReschedule(@RequestBody EmailRequest emailRequest) {
        return eventService.sendHtml(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), recurrenceEvent.recurOnePlusReschedule());
    }

    @ApiOperation(value = "Send recurrence more than one event cancel via mail message")
    @PostMapping(value = "/cancel")
    public EmailResponse sendRecurrenceCancel(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventCancel(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), recurrenceEvent.recurCancel());
    }

    @ApiOperation(value = "Send recurrence single event cancel via mail message")
    @PostMapping(value = "/cancel/single")
    public EmailResponse sendRecurrenceSingleCancel(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventCancel(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), recurrenceEvent.recurSingleCancel());
    }

    @ApiOperation(value = "Send recurrence more than one event cancel via mail message")
    @PostMapping(value = "/cancel/more")
    public EmailResponse sendRecurrenceMoreThanOneCancel(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventCancel(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), recurrenceEvent.recurOnePlusCancel());
    }

}
