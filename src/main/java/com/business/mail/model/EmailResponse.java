package com.business.mail.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;

@Document(collection = "emailResponse")
public class EmailResponse {

    @Id
    private int id;

    @Email
    private String recipientEmail;

    private String messageSubject;

    private Object messageBody;

    private String dateOfResponse;

    private boolean isDelivered;

    public EmailResponse() {
    }

    public EmailResponse(String recipientEmail, String messageSubject, Object messageBody, String dateOfResponse,
                         Boolean isDelivered) {
        this.recipientEmail = recipientEmail;
        this.messageSubject = messageSubject;
        this.messageBody = messageBody;
        this.dateOfResponse = dateOfResponse;
        this.isDelivered = isDelivered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public Object getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(Object messageBody) {
        this.messageBody = messageBody;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    @Override
    public String toString() {
        return "EmailResponse{" +
                "recipientEmail='" + recipientEmail + '\'' +
                ", messageSubject='" + messageSubject + '\'' +
                ", messageBody=" + messageBody +
                ", dateOfResponse='" + dateOfResponse + '\'' +
                ", isDelivered=" + isDelivered +
                '}';
    }
}
