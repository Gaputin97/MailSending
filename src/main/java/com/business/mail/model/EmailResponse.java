package com.business.mail.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;

@Document(collection = "email_response")
public class EmailResponse {
    @Id
    private int _id;

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

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
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

    public String getDateOfResponse() {
        return dateOfResponse;
    }

    public void setDateOfResponse(String dateOfResponse) {
        this.dateOfResponse = dateOfResponse;
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
