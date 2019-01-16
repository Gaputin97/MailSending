package com.business.mail.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;

@Document(collection = "email_request")
public class EmailRequest {
    @ApiModelProperty(notes = "Email who takes mails")
    @Email
    private String[] recipientEmail;

    @ApiModelProperty(notes = "Subject about sending data")
    private String messageSubject;

    public EmailRequest() {
    }

    public EmailRequest(String[] recipientEmail, String messageSubject) {
        this.recipientEmail = recipientEmail;
        this.messageSubject = messageSubject;
    }

    public String[] getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String[] recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    @Override
    public String toString() {
        return "EmailRequest{" +
                "recipientEmail='" + recipientEmail + '\'' +
                ", messageSubject='" + messageSubject + '\'' +
                '}';
    }
}
