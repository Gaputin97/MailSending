package com.business.mail.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;

@Document(collection = "emailRequest")
public class EmailRequest {

    @ApiModelProperty(notes = "Email who takes mails")
    @Email
    private String recipientEmail;

    @ApiModelProperty(notes = "Data which sending to recipient")
    private String messageBody;

    @ApiModelProperty(notes = "Subject about sending data")
    private String messageSubject;

    public EmailRequest() {
    }

    public EmailRequest(String recipientEmail, String messageSubject, String messageBody) {
        this.recipientEmail = recipientEmail;
        this.messageSubject = messageSubject;
        this.messageBody = messageBody;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
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
                ", messageBody=" + messageBody +
                '}';
    }
}
