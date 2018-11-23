package com.business.mail.controller;


import com.business.ical.service.interfaces.LotusNotes;
import com.business.mail.model.EmailRequest;
import com.business.mail.model.EmailResponse;
import com.business.mail.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/send/event/lotus", produces = "application/json", consumes = "application/json")
public class LotusNotesController {
    private EventService eventService;
    private LotusNotes lotusNotes;

    @Autowired
    public LotusNotesController(EventService eventService, LotusNotes lotusNotes) {
        this.eventService = eventService;
        this.lotusNotes = lotusNotes;
    }

    @RequestMapping(value = "/lotus")
    public EmailResponse sendRecurrenceInvite(@RequestBody EmailRequest emailRequest) {
        return eventService.sendEventRequest(emailRequest.getRecipientEmail(),
                emailRequest.getMessageSubject(), lotusNotes.createLotusNotesCalendar());
    }
}
