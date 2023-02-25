package com.lewis.msmessageemail.entities;

import java.io.Serializable;
import java.time.LocalDate;

public class MailEvent implements Serializable {

    private String username;
    private String email;
    private String subject;
    private String text;
    private LocalDate date;

    public MailEvent() {
    }

    public MailEvent(String username,String email, String subject, String text, LocalDate date) {
        this.username = username;
        this.email = email;
        this.subject = subject;
        this.text = text;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MailEvent{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
