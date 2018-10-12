package com.business.mail.model;

public class EmailResponse {
    private String recipientEmail;

    private String messageSubject;

    private Object messageBody;

    private String dateOfResponse;

    private Boolean isDelivered;

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

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getSubjectOfMessage() {
        return messageSubject;
    }

    public void setSubjectOfMessage(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public Object getBodyOfMessage() {
        return messageBody;
    }

    public void setBodyOfMessage(Object messageBody) {
        this.messageBody = messageBody;
    }

    public String getDateOfResponse() {
        return dateOfResponse;
    }

    public void setDateOfResponse(String dateOfResponse) {
        this.dateOfResponse = dateOfResponse;
    }

    public Boolean getDelivered() {
        return isDelivered;
    }

    public void setDelivered(Boolean delivered) {
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
