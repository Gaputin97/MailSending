package com.business.mail.model;

public class Email {

    private String emailRecipient;

    private Object bodySent;

    private String subjectSent;

    private Boolean isDelivered;

    public Email() {
    }

    public Email(String emailRecipient, String subjectSent, Object bodySent, Boolean isDelivered) {
        this.emailRecipient = emailRecipient;
        this.bodySent = bodySent;
        this.subjectSent = subjectSent;
        this.isDelivered = isDelivered;
    }

    public String getEmailRecipient() {
        return emailRecipient;
    }

    public void setEmailRecipient(String emailRecipient) {
        this.emailRecipient = emailRecipient;
    }

    public Object getBodySent() {
        return bodySent;
    }

    public void setBodySent(Object bodySent) {
        this.bodySent = bodySent;
    }

    public String getSubjectSent() {
        return subjectSent;
    }

    public void setSubjectSent(String subjectSent) {
        this.subjectSent = subjectSent;
    }

    public Boolean getDelivered() {
        return isDelivered;
    }

    public void setDelivered(Boolean delivered) {
        isDelivered = delivered;
    }

    @Override
    public String toString() {
        return "Email{" +
                "emailRecipient='" + emailRecipient + '\'' +
                ", bodySent=" + bodySent +
                ", subjectSent='" + subjectSent + '\'' +
                ", isDelivered=" + isDelivered +
                '}';
    }
}
